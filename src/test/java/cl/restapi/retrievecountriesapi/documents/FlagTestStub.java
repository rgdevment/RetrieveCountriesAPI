package cl.restapi.retrievecountriesapi.documents;

import net.datafaker.Faker;

public class FlagTestStub {
    public static Flag create(
            String ico,
            String png,
            String svg,
            String description
    ) {
        Flag flags = new Flag();
        flags.setIco(ico);
        flags.setPng(png);
        flags.setSvg(svg);
        flags.setDescription(description);
        return flags;
    }

    public static Flag random() {
        Faker faker = new Faker();

        String ico = faker.country().flag();
        String png = faker.country().flag();
        String svg = faker.country().flag();
        String description = faker.lorem().characters(10, 50);

        return create(ico, png, svg, description);
    }
}
