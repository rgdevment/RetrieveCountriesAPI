package cl.restapi.retrievecountriesapi.documents;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CountryTestStub {
    public static Country create(
            String name,
            String capital,
            String region,
            String subregion,
            List<String> cities,
            Flag flags
    ) {
        Country country = new Country();
        country.setName(name);
        country.setCapital(capital);
        country.setRegion(region);
        country.setSubregion(subregion);
        country.setCities(cities);
        country.setFlag(flags);

        return country;
    }

    public static Country random() {
        Faker faker = new Faker();
        List<String> cities = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cities.add(faker.address().city());
        }

        Flag flags = FlagTestStub.random();
        return create(
                faker.country().name(),
                faker.country().capital(),
                faker.nation().nationality(),
                faker.nation().language(),
                cities,
                flags
        );
    }

    public static List<Country> randomList(int size) {
        List<Country> countries = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            countries.add(random());
        }
        return countries;
    }
}
