package cl.tica.portfolio.retrievecountriesapi.populate.services;

import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryCities;
import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryData;
import cl.tica.portfolio.retrievecountriesapi.entities.City;
import cl.tica.portfolio.retrievecountriesapi.entities.Country;
import cl.tica.portfolio.retrievecountriesapi.entities.Flag;
import cl.tica.portfolio.retrievecountriesapi.repositories.CityRepository;
import cl.tica.portfolio.retrievecountriesapi.repositories.CountryRepository;
import cl.tica.portfolio.retrievecountriesapi.repositories.FlagRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
                List<City> citiesSet = new ArrayList<>();
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
