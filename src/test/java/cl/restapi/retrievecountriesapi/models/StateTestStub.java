package cl.restapi.retrievecountriesapi.models;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class StateTestStub {
    public static State create(
            String name,
            String code,
            String countryCode,
            Double latitude,
            Double longitude
    ) {
        return new State(
                name,
                code,
                countryCode,
                latitude,
                longitude
        );
    }

    public static State random() {
        Faker faker = new Faker();
        String name = faker.address().state();
        String code = faker.address().countryCode();
        String countryCode = faker.address().countryCode();
        Double latitude = faker.number().randomDouble(2, -90, 90);
        Double longitude = faker.number().randomDouble(2, -90, 90);

        return create(name, code, countryCode, latitude, longitude);
    }

    public static List<State> randomList(int size) {
        List<State> states = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            states.add(random());
        }
        return states;
    }
}
