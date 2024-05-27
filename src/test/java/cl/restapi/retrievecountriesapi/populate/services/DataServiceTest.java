package cl.restapi.retrievecountriesapi.populate.services;

import cl.restapi.retrievecountriesapi.populate.exceptions.FetchDataException;
import cl.restapi.retrievecountriesapi.populate.models.CountryCities;
import cl.restapi.retrievecountriesapi.populate.models.CountryCitiesData;
import cl.restapi.retrievecountriesapi.populate.models.CountryData;
import cl.restapi.retrievecountriesapi.populate.models.Flags;
import cl.restapi.retrievecountriesapi.populate.models.Name;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DataServiceTest {
    @MockBean
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper objectMapper;
    private DataService service;

    @BeforeEach
    void setUp() {
        RestTemplateBuilder restTemplateBuilder = Mockito.mock(RestTemplateBuilder.class);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        this.objectMapper = new ObjectMapper();
        this.service = new DataService(restTemplateBuilder, objectMapper);
    }

    @Test
    void testFetchCountryData() throws JsonProcessingException {
        ReflectionTestUtils.setField(this.service, "countriesApiUrl", "http://mock_url");

        ArrayList<CountryData> countryDataList =
                getCountryData();

        when(restTemplate.getForEntity("http://mock_url", CountryData[].class))
                .thenReturn(ResponseEntity.ok(countryDataList.toArray(new CountryData[0])));

        this.service.fetchCountryData();

        verify(restTemplate, times(1)).getForEntity("http://mock_url", CountryData[].class);
    }

    @Test
    void testFetchCountryCitiesData() {
        ReflectionTestUtils.setField(this.service, "countriesCitiesApiUrl", "https://mock_url/cities");

        List<CountryCities> countryCitiesList = new ArrayList<>();
        countryCitiesList.add(new CountryCities("Chile", Arrays.asList("Antofagasta", "Santiago", "Chiloe")));

        CountryCitiesData countryCitiesData = new CountryCitiesData(countryCitiesList);

        when(restTemplate.getForEntity("https://mock_url/cities", CountryCitiesData.class))
                .thenReturn(ResponseEntity.ok(countryCitiesData));

        this.service.fetchCountryCitiesData();

        verify(restTemplate, times(1)).getForEntity("https://mock_url/cities", CountryCitiesData.class);
    }

    @Test
    void testFetchCountryDataFile() throws IOException {
        ReflectionTestUtils.setField(service, "countriesApiUrl", "classpath:data/countries_example.json");

        Resource resource = Mockito.mock(Resource.class);
        InputStream inputStream = new FileInputStream("src/test/resources/data/countries_example.json");
        when(resource.getInputStream()).thenReturn(inputStream);

        List<CountryData> result = service.fetchCountryData();

        ArrayList<CountryData> countryDataList =
                getCountryData();

        List<CountryData> expectedResult = List.of(countryDataList.getFirst());

        CountryData expectedResultFirst = expectedResult.getFirst();
        CountryData resultFirst = result.getFirst();

        assertEquals(expectedResultFirst.name().getCommon(), resultFirst.name().getCommon());
        assertEquals(expectedResultFirst.capital().getFirst(), resultFirst.capital().getFirst());
        assertEquals(expectedResultFirst.region(), resultFirst.region());
        assertEquals(expectedResultFirst.subregion(), resultFirst.subregion());
        assertEquals(expectedResultFirst.flag(), resultFirst.flag());
    }

    @Test
    void testFetchCountryCitiesDataFile() throws IOException {
        ReflectionTestUtils.setField(service, "countriesCitiesApiUrl", "classpath:data/cities_example.json");

        Resource resource = Mockito.mock(Resource.class);
        InputStream inputStream = new FileInputStream("src/test/resources/data/cities_example.json");
        when(resource.getInputStream()).thenReturn(inputStream);

        List<CountryCities> result = service.fetchCountryCitiesData();

        List<CountryCities> expectedResult =
                List.of(new CountryCities("Chile", Arrays.asList("Antofagasta", "Iquique")));
        assertEquals(expectedResult, result);
    }

    @Test
    void testFetchCountryDataFileNotFound() throws IOException {
        ReflectionTestUtils
                .setField(service, "countriesApiUrl", "classpath:data/countries_example.json.fake");

        Resource resource = Mockito.mock(Resource.class);
        when(resource.getInputStream()).thenThrow(new IOException("Test exception"));

        assertThrows(FetchDataException.class, () -> service.fetchCountryData());
    }

    @Test
    void testFetchCountryCitiesDataFileNotFound() throws IOException {
        ReflectionTestUtils
                .setField(service, "countriesCitiesApiUrl", "classpath:data/cities_example.json.fake");

        Resource resource = Mockito.mock(Resource.class);
        when(resource.getInputStream()).thenThrow(new IOException("Test exception"));

        assertThrows(FetchDataException.class, () -> service.fetchCountryCitiesData());
    }

    private static ArrayList<CountryData> getCountryData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonChile = "{\"common\":\"Chile\"}";
        JsonNode jsonNameChile = mapper.readTree(jsonChile);

        ArrayList<CountryData> countryDataList = new ArrayList<>();

        String cities = "Santiago";
        List<String> capitals = List.of(cities);

        CountryData countryData1 =
                new CountryData(new Name(jsonNameChile), capitals, "Americas", "South America",
                        "🇨🇱", new Flags("", "", ""));

        countryDataList.add(countryData1);
        return countryDataList;
    }
}
