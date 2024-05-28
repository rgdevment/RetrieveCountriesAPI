package cl.restapi.retrievecountriesapi.models;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class StateTestStub {
    public static State create(
            String name,
            String code,
            Double latitude,
            Double longitude,
            List<City> cities
    ) {
        State state = new State();
        state.setName(name);
        state.setCode(code);
        state.setLatitude(latitude);
        state.setLongitude(longitude);
        state.setCities(cities);

        return state;
    }

    public static State random() {
        return random(5);
    }

    public static State random(int size) {
        Faker faker = new Faker();
        String name = faker.address().state();
        String code = faker.address().countryCode();
        Double latitude = faker.number().randomDouble(2, -90, 90);
        Double longitude = faker.number().randomDouble(2, -90, 90);
        List<City> cities = CityTestStub.randomList(size);

        return create(name, code, latitude, longitude, cities);
    }

    public static List<State> randomList(int size) {
        List<State> states = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            states.add(random(size * 2));
        }
        return states;
    }
}
