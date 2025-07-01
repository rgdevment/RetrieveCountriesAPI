package cl.restapi.retrievecountriesapi.models;

import net.datafaker.Faker;

public class CurrencyTestStub {
    public static Currency create(
            String name,
            String code,
            String symbol
    ) {
        return new Currency(
                name,
                code,
                symbol
        );
    }

    public static Currency random() {
        Faker faker = new Faker();
        String name = faker.money().currency();
        String code = faker.money().currencyCode();
        String symbol = faker.money().currencySymbol();
        return create(name, code, symbol);
    }
}
