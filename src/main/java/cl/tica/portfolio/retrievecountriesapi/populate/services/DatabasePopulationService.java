package cl.tica.portfolio.retrievecountriesapi.populate.services;

import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryCities;
import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryData;
import cl.tica.portfolio.retrievecountriesapi.documents.Country;
import cl.tica.portfolio.retrievecountriesapi.documents.Flag;
import cl.tica.portfolio.retrievecountriesapi.repositories.CountryRepository;
import cl.tica.portfolio.retrievecountriesapi.repositories.FlagRepository;
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
    private final FlagRepository flagRepository;

    public DatabasePopulationService(DataService dataService, CountryRepository countryRepository,
                                     FlagRepository flagRepository) {
        this.dataService = dataService;
        this.countryRepository = countryRepository;
        this.flagRepository = flagRepository;
    }

    public void populateDatabase() {
        cleanDatabase();

        List<CountryData> countryDataList = dataService.fetchCountryData();
        Map<String, List<String>> countryCitiesMap = dataService.fetchCountryCitiesData().stream()
                .collect(Collectors.toMap(CountryCities::country, CountryCities::cities));

        for (CountryData countryData : countryDataList) {
            Flag flags = instanceFlags(countryData);
            List<String> cities = instanceCities(countryData, countryCitiesMap);
            Country country = instanceCountry(countryData, flags, cities);

            persistData(flags, country);
        }
    }

    private static Flag instanceFlags(CountryData countryData) {
        Flag flags = new Flag();
        flags.setIco(countryData.flag());
        flags.setPng(countryData.flags().png());
        flags.setSvg(countryData.flags().svg());
        flags.setDescription(countryData.flags().alt());
        return flags;
    }

    private static List<String> instanceCities(CountryData countryData, Map<String, List<String>> countryCitiesMap) {
        return countryCitiesMap.getOrDefault(countryData.name().getCommon(), new ArrayList<>());
    }

    private static Country instanceCountry(CountryData countryData, Flag flags, List<String> cities) {
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

        country.setFlag(flags);
        country.setCities(cities);
        return country;
    }

    private void persistData(Flag flags, Country country) {
        flagRepository.save(flags);
        countryRepository.save(country);
    }

    private void cleanDatabase() {
        countryRepository.deleteAll();
        flagRepository.deleteAll();
    }
}
