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
            if (countryData.capital() != null && !countryData.capital().isEmpty()) {
                country.getCapitals().addAll(countryData.capital());
            }
            country.setRegion(countryData.region());

            if (countryData.subregion() != null && !countryData.subregion().isBlank()) {
                country.setSubregion(countryData.subregion());
            } else {
                country.setSubregion("N/A");
            }

            Flag flagPNG = new Flag();
            flagPNG.setFormat("png");
            flagPNG.setPath(countryData.flags().png());
            flagPNG.setCountry(country);

            Flag flagSVG = new Flag();
            flagSVG.setFormat("svg");
            flagSVG.setPath(countryData.flags().svg());
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
                    city.setPopulation(0);
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
