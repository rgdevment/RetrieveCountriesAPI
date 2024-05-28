package cl.restapi.retrievecountriesapi.models;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CityTestStub {
    public static City create(
            String name,
            String stateCode,
            String countryCode,
            Double latitude,
            Double longitude
    ) {
        City city = new City();
        city.setName(name);
        city.setStateCode(stateCode);
        city.setCountryCode(countryCode);
        city.setLatitude(latitude);
        city.setLongitude(longitude);

        return city;
    }

    public static City random() {
        Faker faker = new Faker();
        String name = faker.address().city();
        String stateCode = faker.address().countryCode();
        String countryCode = faker.address().countryCode();
        Double latitude = faker.number().randomDouble(2, -90, 90);
        Double longitude = faker.number().randomDouble(2, -90, 90);
        return create(name, stateCode, countryCode, latitude, longitude);
    }

    public static List<City> randomList(int size) {
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cities.add(random());
        }
        return cities;
    }
}
