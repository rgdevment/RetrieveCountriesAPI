# RetrieveCountriesAPI

[![Sponsor rgdevment](https://img.shields.io/badge/Sponsor-rgdevment-blue?logo=github)](https://github.com/sponsors/rgdevment)
[![Build Status](https://github.com/rgdevment/RetrieveCountriesAPI/actions/workflows/main.yml/badge.svg)](https://github.com/rgdevment/RetrieveCountriesAPI/actions/workflows/main.yml)
[![Coverage](https://rgdevment.github.io/RetrieveCountriesAPI/badges/jacoco.svg)](https://rgdevment.github.io/RetrieveCountriesAPI)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=rgdevment_RetrieveCountriesAPI&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=rgdevment_RetrieveCountriesAPI)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

RetrieveCountriesAPI is an open-source REST API licensed under MIT that allows you to query data about countries, cities, and other relevant information worldwide. This API is continuously developing and growing.

## Available in other languages:
- [Español (Spanish)](README.md)

## Documentation

- [Swagger Documentation](https://countries.apirest.cl/docs)
- [Postman API Documentation](https://www.postman.com/rgdevment/workspace/retrievecountriesapi/overview)

## Example Usage

You can get information about a country and its cities with this simple call:

	curl -X GET "https://countries.apirest.cl/v1/chile"

Or, if you prefer, you can get all the countries in a specific region with:

	curl -X GET "https://countries.apirest.cl/v1/region/americas"

You could even get all the countries in the world with a single request:

	curl -X GET "https://countries.apirest.cl/v1/all"

You can also show or hide additional information with the following **optional parameters**:

- `excludeCities` (optional): boolean
- `excludeStates` (optional): boolean

For more information and other endpoints, please refer to the Postman API Documentation or Swagger Documentation.

## Running the Project Locally

### Requirements

- **Java**: 22
- **Spring Boot**: 3.x
- **Gradle**: Use the included `gradlew` wrapper.

### Installation

1. Clone the repository:
   - git clone https://github.com/rgdevment/RetrieveCountriesAPI.git
   - cd RetrieveCountriesAPI

2. Run the application:
   - For development mode: `./gradlew bootRun`
   - For production build: `./gradlew build`

3. Access the API locally at:
   - http://localhost:8080

## Donations

This project is maintained as a free service for everyone. If you find this API useful and would like to support its maintenance, you can make a voluntary donation.

Donations will be used exclusively to cover infrastructure costs, including:

- **Google Cloud Run**: This service hosts and runs the API, with costs for CPU, memory, and runtime.
- **Domain**: The costs of registering and maintaining domains like `apirest.cl` and `restapi.cl`.
- **Storage**: If necessary, funds will cover the costs of external databases or storage services.
- **Network Traffic**: Additional costs related to network usage and data transfer.
- **SSL Certificates**: Part of the donations will go to the purchase or renewal of SSL certificates for secure connections, if needed.

Any contribution is greatly appreciated and will help keep the service running and available for everyone!

Consider [becoming a sponsor](https://github.com/sponsors/rgdevment). Thank you for your support!

## License

This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/). See the [LICENSE](LICENSE.md) file for more details.
