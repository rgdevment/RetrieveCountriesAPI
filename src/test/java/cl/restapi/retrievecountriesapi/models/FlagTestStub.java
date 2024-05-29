package cl.restapi.retrievecountriesapi.models;

import net.datafaker.Faker;

public class FlagTestStub {
    public static Flag create(
            String ico,
            String png,
            String svg,
            String alt
    ) {
        return new Flag(
                ico,
                png,
                svg,
                alt
        );
    }

    public static Flag random() {
        Faker faker = new Faker();
        String ico = faker.internet().image();
        String png = faker.internet().image();
        String svg = faker.internet().image();
        String alt = faker.superhero().descriptor();
        return create(ico, png, svg, alt);
    }
}
