package cl.restapi.retrievecountriesapi.models;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CityTestStub {
    public static City create(
            String name,
            Double latitude,
            Double longitude
    ) {
        City city = new City();
        city.setName(name);
        city.setLatitude(latitude);
        city.setLongitude(longitude);

        return city;
    }

    public static City random() {
        Faker faker = new Faker();
        String name = faker.address().city();
        Double latitude = faker.number().randomDouble(2, -90, 90);
        Double longitude = faker.number().randomDouble(2, -90, 90);
        return create(name, latitude, longitude);
    }

    public static List<City> randomList(int size) {
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cities.add(random());
        }
        return cities;
    }
}
