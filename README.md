# Unit Converter API
A RESTful API for converting between units of measurement, supporting temperature, length, weight, and time conversions.
Built with **Spring Boot 3**, **Java 17**, and **Swagger/OpenAPI** for interactive documentation.

## Features
- Convert between units of (temperature, length, weight or time).
- List supported measurement categories.
- List supported measurement units for each category.
- Robust error handling for unsupported categories or units.
- Swagger UI for interactive API docs.

## Endpoints
| Method | Endpoint                               | Description                     |
|--------|----------------------------------------|---------------------------------|
| POST   | `/api/convert` | Convert a value between units |
| GET    | `/api/categories` | List supported measurement categories |
| GET    | `/api/units?category={category}` | List units in a category |
| GET    | `/api/sample-payload` | Sample `/convert` request body |
| GET    | `/api/health` | API status |

##  Installation & Setup
1. Clone the Repository:

```
git clone https://github.com/your-username/unit-converter-api.git
cd unit-converter-api
```

2. Build & Run

Make sure you have Java 17+ and Maven installed.

```
mvn clean install
mvn spring-boot:run
```
The API will be available at:

`http://localhost:8080`

## API Documentation
Swagger UI will be available at:

`http://localhost:8080/swagger-ui/index.html`
 
