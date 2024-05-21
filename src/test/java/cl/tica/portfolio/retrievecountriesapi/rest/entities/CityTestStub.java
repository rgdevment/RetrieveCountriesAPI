package cl.tica.portfolio.retrievecountriesapi.rest.entities;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CityTestStub {
    public static City create(
            String name
    ) {
        City city = new City();
        city.setName(name);
        return city;
    }

    public static City random() {
        Faker faker = new Faker();
        String name = faker.address().cityName();
        return create(name);
    }

    public static Set<City> randomList(int size) {
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cities.add(random());
        }
        return Set.copyOf(cities);
    }
}
