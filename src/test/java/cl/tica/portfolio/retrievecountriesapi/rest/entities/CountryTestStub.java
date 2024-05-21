package cl.tica.portfolio.retrievecountriesapi.rest.entities;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CountryTestStub {
    public static Country create(
            String name,
            String capital,
            String region,
            String subregion,
            Set<City> cities,
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
        Set<City> cities = CityTestStub.randomList(6);
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
