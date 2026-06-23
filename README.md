# User Service - Microservicio de Usuarios y Brigadas

Spring Boot 3.4.x para la gestión de ciudadanos, brigadistas y administradores del sistema de emergencias Valle del Sol.

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/usuario` | Listar todos los usuarios |
| GET | `/api/usuario/{id}` | Obtener usuario por ID |
| GET | `/api/usuario/rut/{rut}` | Buscar usuario por RUT |
| POST | `/api/usuario` | Crear usuario |
| PUT | `/api/usuario/{id}` | Actualizar usuario |
| DELETE | `/api/usuario/{id}` | Eliminar usuario |
| GET | `/api/brigada` | Listar brigadas |
| POST | `/api/brigada` | Crear brigada |

## Tecnologías

- Spring Boot 3.4.4 / Java 17
- Spring Data JPA (MySQL)
- Eureka Client
- JaCoCo (cobertura ≥ 60%)

## Tests

```bash
mvnw test
```
