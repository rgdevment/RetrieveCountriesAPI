# RetrieveCountriesAPI

[![Patrocina a rgdevment](https://img.shields.io/badge/Sponsor-rgdevment-blue?logo=github)](https://github.com/sponsors/rgdevment)
[![Estado de la compilación](https://github.com/rgdevment/RetrieveCountriesAPI/actions/workflows/main.yml/badge.svg)](https://github.com/rgdevment/RetrieveCountriesAPI/actions/workflows/main.yml)
[![Cobertura](https://rgdevment.github.io/RetrieveCountriesAPI/badges/jacoco.svg)](https://rgdevment.github.io/RetrieveCountriesAPI)
[![Estado de la calidad](https://sonarcloud.io/api/project_badges/measure?project=rgdevment_RetrieveCountriesAPI&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=rgdevment_RetrieveCountriesAPI)
[![Licencia: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

RetrieveCountriesAPI es una API REST de código abierto bajo la licencia MIT que te permite consultar datos sobre países, ciudades y otra información relevante en todo el mundo. Esta API está en continuo desarrollo y crecimiento.

## Disponible en otros idiomas:
- [English (Inglés)](README.en.md)

## Documentación

- [Documentación Swagger](https://countries.apirest.cl/docs)
- [Documentación API en Postman](https://www.postman.com/rgdevment/workspace/retrievecountriesapi/overview)

## Ejemplos de uso

Puedes obtener información sobre un país y sus ciudades con esta simple llamada:

	curl -X GET "https://countries.apirest.cl/v1/chile"

O, si lo prefieres, puedes obtener todos los países de una región específica:

	curl -X GET "https://countries.apirest.cl/v1/region/americas"

Incluso puedes obtener todos los países del mundo con una sola petición:

	curl -X GET "https://countries.apirest.cl/v1/all"

También puedes mostrar u ocultar información adicional con los siguientes **parámetros opcionales**:

- `excludeCities` (opcional): booleano
- `excludeStates` (opcional): booleano

Para más información y otros endpoints, consulta la Documentación en Postman o Swagger.

## Ejecución del proyecto localmente

### Requisitos

- **Java**: 22
- **Spring Boot**: 3.x
- **Gradle**: Utiliza el wrapper `gradlew` incluido.

### Instalación

1. Clona el repositorio:
    - git clone https://github.com/rgdevment/RetrieveCountriesAPI.git
    - cd RetrieveCountriesAPI

2. Ejecuta la aplicación:
    - Para modo desarrollo: `./gradlew bootRun`
    - Para construir producción: `./gradlew build`

3. Accede a la API localmente en:
    - http://localhost:8080

## Donaciones

Este proyecto se mantiene de manera gratuita para todos. Si encuentras útil esta API y deseas apoyar su mantenimiento, puedes contribuir con una donación voluntaria.

Las donaciones se destinarán exclusivamente a cubrir los costos de infraestructura, que incluyen:

- **Google Cloud Run**: Servicio que aloja y ejecuta la API, cubriendo los costos de CPU, memoria y tiempo de ejecución.
- **Dominio**: Los costos del registro y mantenimiento de los dominios `apirest.cl` y `restapi.cl`.
- **Almacenamiento**: Si es necesario, los fondos cubrirán los costos de bases de datos externas o servicios de almacenamiento.
- **Tráfico de red**: Costos adicionales relacionados con el uso de red y la transferencia de datos.
- **Certificados SSL**: Parte de las donaciones se destinarán a la compra o renovación de certificados SSL para conexiones seguras, si es necesario.

¡Cualquier aporte es bienvenido y ayudará a mantener el servicio activo y disponible para todos!

Considera [ser un patrocinador](https://github.com/sponsors/rgdevment). ¡Gracias por tu apoyo!

## Licencia

Este proyecto está licenciado bajo la [Licencia MIT](https://choosealicense.com/licenses/mit/). Consulta el archivo [LICENSE](LICENSE.md) para más detalles.
