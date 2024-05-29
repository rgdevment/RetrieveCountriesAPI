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

A simple request:

```sh
curl -X GET "https://countries.restapi.cl/v1/all"
```

To get a specific country:

```sh
curl -X GET "https://countries.restapi.cl/v1/chile"
```

If you need all regions with their states:

```sh
curl -X GET "https://countries.restapi.cl/v1/region/americas"
```

In addition to the above, you can also obtain additional information with the following **parameters**

- `includeCities` (optional): boolean
- `includeStates` (optional): boolean

For more information and other endpoints, please refer to the Postman API Documentation or Swagger Documentation.

## License

This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/). See
the [LICENSE](LICENSE.md) file for more details.

## 🌟 **Thank you for using this API!**

Our API is a **public service**, **freely available** for use, and it's **open-source**.

We have **limited resources**. To help us efficiently serve everyone, please **cache requests** in your application.
This will reduce unnecessary resource consumption.

Please maintain **good practices and behavior** to ensure we can continue providing this service **freely and openly**
to all who need it.

---

## 🌟 **¡Gracias por utilizar esta API!**

Nuestra API es un **servicio público**, **disponible de forma gratuita** para su uso, y es **de código abierto**.

Contamos con **recursos limitados**. Para ayudarnos a servir eficientemente a todos, por favor **almacene en caché las
solicitudes** en su aplicación. Esto reducirá el consumo innecesario de recursos.

Por favor, mantenga **buenas prácticas y comportamiento** para asegurar que podamos continuar proporcionando este
servicio **libre y abiertamente** para todos aquellos que lo necesiten.
