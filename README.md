[![Build Status](https://github.com/rgdevment/RetrieveCountriesAPI/actions/workflows/main.yml/badge.svg)](https://github.com/rgdevment/RetrieveCountriesAPI/actions/workflows/main.yml)
[![Coverage](https://rgdevment.github.io/RetrieveCountriesAPI/badges/jacoco.svg)](https://rgdevment.github.io/RetrieveCountriesAPI) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=rgdevment_RetrieveCountriesAPI&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=rgdevment_RetrieveCountriesAPI)

# RetrieveCountriesAPI

Retrieve Countries API is an open-source REST API licensed under MIT that allows you to query data about countries,
cities, and other relevant information worldwide. This API is continuously developing and growing.

## Documentation

- [Swagger Documentation](https://countries.tica.cl/docs)
- [Postman API Documentation](https://www.postman.com/rgdevment/workspace/retrievecountriesapi/overview)

## Example Usage

### Get All Countries

#### GET /v1/countries

Retrieves all country data with an option to exclude cities.

**Parameters:**

- `excludeCities` (optional): boolean

**Example Usage:**

```sh
curl -X GET "https://countries.tica.cl/v1/countries"
```

or exclude cities

```sh
curl -X GET "https://countries.tica.cl/v1/countries?excludeCities=true"
```

For more information and other endpoints, please refer to the Postman API Documentation or Swagger Documentation.

## License

This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/). See
the [LICENSE](LICENSE.md) file for more details.