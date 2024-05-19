package cl.tica.portfolio.retrievecountriesapi.populate.services;

import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryCities;
import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryData;
import cl.tica.portfolio.retrievecountriesapi.rest.entities.City;
import cl.tica.portfolio.retrievecountriesapi.rest.entities.Country;
import cl.tica.portfolio.retrievecountriesapi.rest.entities.Flag;
import cl.tica.portfolio.retrievecountriesapi.rest.enums.FlagFormat;
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

            country.setFlag(countryData.flag());

            Flag flagPNG = new Flag();
            flagPNG.setFormat(FlagFormat.PNG);
            flagPNG.setPath(countryData.flags().png());
            flagPNG.setDescription(countryData.flags().alt());
            flagPNG.setCountry(country);

            Flag flagSVG = new Flag();
            flagSVG.setFormat(FlagFormat.SVG);
            flagSVG.setPath(countryData.flags().svg());
            flagSVG.setDescription(countryData.flags().alt());
            flagSVG.setCountry(country);

            Set<Flag> flags = new HashSet<>();
            flags.add(flagPNG);
            flags.add(flagSVG);

            country.setFlags(flags);

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
            flagRepository.save(flagPNG);
            flagRepository.save(flagSVG);
        }
    }
}
