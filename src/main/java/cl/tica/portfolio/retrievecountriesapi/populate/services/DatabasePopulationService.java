package cl.tica.portfolio.retrievecountriesapi.populate.services;

import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryCities;
import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryData;
import cl.tica.portfolio.retrievecountriesapi.rest.entities.City;
import cl.tica.portfolio.retrievecountriesapi.rest.entities.Country;
import cl.tica.portfolio.retrievecountriesapi.rest.entities.Flag;
import cl.tica.portfolio.retrievecountriesapi.rest.repositories.CityRepository;
import cl.tica.portfolio.retrievecountriesapi.rest.repositories.CountryRepository;
import cl.tica.portfolio.retrievecountriesapi.rest.repositories.FlagRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DatabasePopulationService {

    private final DataService dataService;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final FlagRepository flagRepository;

    public DatabasePopulationService(DataService dataService, CountryRepository countryRepository,
                                     CityRepository cityRepository, FlagRepository flagRepository) {
        this.dataService = dataService;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.flagRepository = flagRepository;
    }

    @Transactional
    public void populateDatabase() {
        List<CountryData> countryDataList = dataService.fetchCountryData();
        Map<String, List<String>> countryCitiesMap = dataService.fetchCountryCitiesData().stream()
                .collect(Collectors.toMap(CountryCities::country, CountryCities::cities));

        for (CountryData countryData : countryDataList) {
            Country country = new Country();
            country.setName(countryData.name().getCommon());
            country.setRegion(countryData.region());

            country.setCapital(Optional.ofNullable(countryData.capital())
                    .filter(capital -> !capital.isEmpty())
                    .map(List::getFirst)
                    .orElse("N/A"));

            country.setSubregion(Optional.ofNullable(countryData.subregion())
                    .filter(subregion -> !subregion.isBlank())
                    .orElse("N/A"));

            Flag flags = new Flag();
            flags.setIco(countryData.flag());
            flags.setPng(countryData.flags().png());
            flags.setSvg(countryData.flags().svg());
            flags.setDescription(countryData.flags().alt());
            flags.setCountry(country);

            country.setFlag(flags);

            countryRepository.save(country);

            List<String> cities = countryCitiesMap.get(countryData.name().getCommon());
            if (cities != null) {
                Set<City> citiesSet = new HashSet<>();
                for (String cityName : cities) {
                    City city = new City();
                    city.setName(cityName);
                    city.setCountry(country);
                    cityRepository.save(city);
                    citiesSet.add(city);
                }
                country.setCities(citiesSet);
            }

            countryRepository.save(country);
            flagRepository.save(flags);
        }
    }
}
