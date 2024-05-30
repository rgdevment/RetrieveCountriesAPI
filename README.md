[![Build Status](https://github.com/rgdevment/RetrieveCountriesAPI/actions/workflows/main.yml/badge.svg)](https://github.com/rgdevment/RetrieveCountriesAPI/actions/workflows/main.yml)
[![Coverage](https://rgdevment.github.io/RetrieveCountriesAPI/badges/jacoco.svg)](https://rgdevment.github.io/RetrieveCountriesAPI)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=rgdevment_RetrieveCountriesAPI&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=rgdevment_RetrieveCountriesAPI)

# RetrieveCountriesAPI

RetrieveCountriesAPI is an open-source REST API licensed under MIT that allows you to query data about countries,
cities, and other relevant information worldwide. This API is continuously developing and growing.

## Documentation

- [Swagger Documentation](https://countries.restapi.cl/docs)
- [Postman API Documentation](https://www.postman.com/rgdevment/workspace/retrievecountriesapi/overview)

## Example Usage

You can get information about a country and its cities with this simple call:

```sh
curl -X GET "https://countries.restapi.cl/v1/chile"
```

Or if you prefer, you can get all the countries in a specific region with:

```sh
curl -X GET "https://countries.restapi.cl/v1/region/americas"
```

You could even get all the countries in the world with a single request:

```sh
curl -X GET "https://countries.restapi.cl/v1/all"
```

In addition to the above, you can also show or hide additional information with the following **optional parameters**:

- `excludeCities` (optional): boolean
- `excludeStates` (optional): boolean

For more information and other endpoints, please refer to the Postman API Documentation or Swagger Documentation.

## 🌟 **Thank you for using this API!**

Our API is a **public service**, **freely available** for use, and it's **open-source**.

We have **limited resources**. To help us efficiently serve everyone, please **cache requests** in your application.
This will reduce unnecessary resource consumption.

Please maintain **good practices and behavior** to ensure we can continue providing this service **freely and openly**
to all who need it.

## License

This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/). See
the [LICENSE](LICENSE.md) file for more details.

