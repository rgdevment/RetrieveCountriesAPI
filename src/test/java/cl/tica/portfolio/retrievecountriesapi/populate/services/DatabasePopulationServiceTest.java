package cl.tica.portfolio.retrievecountriesapi.populate.services;

import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryCities;
import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryData;
import cl.tica.portfolio.retrievecountriesapi.populate.models.Flags;
import cl.tica.portfolio.retrievecountriesapi.populate.models.Name;
import cl.tica.portfolio.retrievecountriesapi.documents.Country;
import cl.tica.portfolio.retrievecountriesapi.documents.Flag;
import cl.tica.portfolio.retrievecountriesapi.repositories.CountryRepository;
import cl.tica.portfolio.retrievecountriesapi.repositories.FlagRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DatabasePopulationServiceTest {
    private CountryRepository countryRepository;
    private FlagRepository flagRepository;

    private DataService dataService;
    private DatabasePopulationService service;

    @BeforeEach
    void setUp() {
        this.countryRepository = mock(CountryRepository.class);
        this.flagRepository = mock(FlagRepository.class);
        this.dataService = mock(DataService.class);

        this.service = new DatabasePopulationService(dataService, countryRepository, flagRepository);
    }

    @Test
    void testPopulateDatabase() throws JsonProcessingException {
        ArrayList<CountryData> countryDataList =
                getCountryData();

        Map<String, List<String>> countryCitiesMap = new HashMap<>();
        countryCitiesMap.put("Chile", Arrays.asList("Antofagasta", "Santiago", "Chiloe"));

        when(dataService.fetchCountryData()).thenReturn(countryDataList);
        when(dataService.fetchCountryCitiesData()).thenReturn(countryCitiesMap.entrySet().stream()
                .map(entry -> new CountryCities(entry.getKey(), entry.getValue()))
                .toList());

        service.populateDatabase();

        verify(dataService, times(1)).fetchCountryData();
        verify(dataService, times(1)).fetchCountryCitiesData();

        verify(countryRepository, times(countryDataList.size())).save(any(Country.class));
        verify(flagRepository, times(countryDataList.size())).save(any(Flag.class));
    }

    private static ArrayList<CountryData> getCountryData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonChile = "{\"common\":\"Chile\"}";
        JsonNode jsonNameChile = mapper.readTree(jsonChile);
        String jsonOther = "\"Other\"";
        JsonNode jsonNameOther = mapper.readTree(jsonOther);

        ArrayList<CountryData> countryDataList = new ArrayList<>();

        String cities = "Santiago";
        List<String> capitals = List.of(cities);

        CountryData countryData1 =
                new CountryData(new Name(jsonNameChile), capitals, "Americas", "South America",
                        "🇨🇱", new Flags("", "", ""));

        CountryData countryData2 =
                new CountryData(new Name(jsonNameOther), new ArrayList<>(), "Americas", "", "🇺🇸",
                        new Flags("", "", ""));

        countryDataList.add(countryData1);
        countryDataList.add(countryData2);
        return countryDataList;
    }
}
