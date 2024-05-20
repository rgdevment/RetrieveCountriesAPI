package cl.tica.portfolio.retrievecountriesapi.populate.services;

import cl.tica.portfolio.retrievecountriesapi.populate.exceptions.FetchDataException;
import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryCities;
import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryCitiesData;
import cl.tica.portfolio.retrievecountriesapi.populate.models.CountryData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DataService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${countries.json_file}")
    private String countriesApiUrl;

    @Value("${cities.json_file}")
    private String countriesCitiesApiUrl;

    public DataService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public List<CountryData> fetchCountryData() {
        try {
            if (isWebFile(countriesApiUrl)) {
                ResponseEntity<CountryData[]> response =
                        restTemplate.getForEntity(countriesApiUrl, CountryData[].class);
                return Arrays.asList(Objects.requireNonNull(response.getBody()));
            } else {
                String resourcePath = countriesApiUrl.replaceFirst("classpath:", "");
                Resource resource = new ClassPathResource(resourcePath);
                InputStream inputStream = resource.getInputStream();
                return Arrays.asList(this.objectMapper.readValue(inputStream, CountryData[].class));
            }
        } catch (Exception e) {
            throw new FetchDataException(HttpStatus.GATEWAY_TIMEOUT, "Failed to fetch country data");
        }
    }

    public List<CountryCities> fetchCountryCitiesData() {
        try {
            if (isWebFile(countriesCitiesApiUrl)) {
                ResponseEntity<CountryCitiesData> response =
                        restTemplate.getForEntity(countriesCitiesApiUrl, CountryCitiesData.class);
                return Objects.requireNonNull(response.getBody()).data();
            } else {
                String resourcePath = countriesCitiesApiUrl.replaceFirst("classpath:", "");
                Resource resource = new ClassPathResource(resourcePath);
                InputStream inputStream = resource.getInputStream();
                CountryCitiesData countryCitiesData = this.objectMapper.readValue(inputStream, CountryCitiesData.class);
                return countryCitiesData.data();
            }
        } catch (Exception e) {
            throw new FetchDataException(HttpStatus.GATEWAY_TIMEOUT, "Failed to fetch country cities data");
        }
    }

    private boolean isWebFile(String urlString) {
        return urlString.startsWith("http:") || urlString.startsWith("https:");
    }
}
