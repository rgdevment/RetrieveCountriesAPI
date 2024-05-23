package cl.tica.portfolio.retrievecountriesapi.application.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    OpenAPI openApi() {
        License apacheLicence = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Contact contact = new Contact();
        contact.name("Retrieve Countries API");

        Server server = new Server();
        server.url("https://countries.tica.cl");

        return new OpenAPI()
                .info(new Info().title("Retrieve Countries API")
                        .description("API to retrieve countries and their information.")
                        .version("0.0.1-SNAPSHOT")
                        .contact(contact)
                        .license(apacheLicence)
                ).servers(List.of(server));
    }
}
