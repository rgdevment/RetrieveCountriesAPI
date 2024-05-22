package cl.tica.portfolio.retrievecountriesapi.populate;

import cl.tica.portfolio.retrievecountriesapi.RetrieveCountriesApiApplication;
import cl.tica.portfolio.retrievecountriesapi.populate.services.DatabasePopulationService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * This class is used to populate the database with information about countries and cities.
 * You might need to modify the models to match your JSON format before proceeding.
 *
 * <p><strong>Note:</strong> Use this class only in a development environment. After populating your development
 * database, you can update your data in production.</p>
 *
 * <p>This class accepts JSON files from both local files and web URLs.</p>
 *
 * <p>For example, you can visit our sample API site to get our data and populate your database:</p>
 * <pre>{@code
 * http://example.com/api/countries
 * http://example.com/api/cities
 * }</pre>
 *
 * <p>or you can just use the example files in the resources folder of this repository</p>
 *
 * <h2>Usage example:</h2>
 * <pre>{@code
 * // Command to populate your database
 * ./gradlew populate
 * }</pre>
 */
public class Populate {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RetrieveCountriesApiApplication.class, args);
        DatabasePopulationService databasePopulationService = context.getBean(DatabasePopulationService.class);
        databasePopulationService.populateDatabase();
        context.close();
    }
}
