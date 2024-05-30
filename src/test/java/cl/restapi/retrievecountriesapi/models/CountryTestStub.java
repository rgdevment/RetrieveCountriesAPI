package cl.restapi.retrievecountriesapi.models;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CountryTestStub {
    public static Country create(
            String name,
            String capital,
            String region,
            String subregion,
            String iso2,
            String iso3,
            String tld,
            String phoneCode,
            Double latitude,
            Double longitude,
            Flag flags,
            Currency currency,
            List<City> cities,
            List<State> states
    ) {
        Country country = new Country();
        country.setName(name);
        country.setCapital(capital);
        country.setRegion(region);
        country.setSubregion(subregion);
        country.setCode(iso2);
        country.setIso3(iso3);
        country.setTld(tld);
        country.setPhoneCode(phoneCode);
        country.setLatitude(latitude);
        country.setLongitude(longitude);
        country.setFlags(flags);
        country.setCurrency(currency);

        country.setStates(states);
        country.setCities(cities);

        return country;
    }

    public static Country random() {
        Faker faker = new Faker();

        String name = faker.country().name();
        String capital = faker.country().capital();
        String region = "Americas";
        String subregion = "South America";
        String iso2 = faker.country().countryCode2();
        String iso3 = faker.country().countryCode3();
        String tld = faker.internet().domainSuffix();
        String phoneCode = faker.phoneNumber().phoneNumberNational();
        Double latitude = faker.number().randomDouble(2, -90, 90);
        Double longitude = faker.number().randomDouble(2, -90, 90);

        Currency currency = CurrencyTestStub.random();
        Flag flags = FlagTestStub.random();

        List<City> cities = CityTestStub.randomList(5);
        List<State> states = StateTestStub.randomList(5);

        return create(name, capital, region, subregion, iso2, iso3, tld, phoneCode, latitude, longitude, flags,
                currency, cities, states);
    }

    public static List<Country> randomList(int size) {
        List<Country> countries = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            countries.add(random());
        }
        return countries;
    }
}
