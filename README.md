# Sistema de Reserva de Salas - Spring Boot 3 con OpenAPI

Una API REST para gestionar salas y reservas con autenticación básica HTTP, migraciones automáticas de base de datos y documentación OpenAPI/Swagger. El proyecto implementa arquitectura limpia con capas de dominio, aplicación e infraestructura.

## 📋 Requisitos Previos

- Java 17+
- PostgreSQL 12+
- Gradle 8.0+

## 🚀 Cómo Ejecutar

### 1. Configurar Base de Datos

```bash
# Crear base de datos en PostgreSQL
createdb qa_bit_demo

# Verificar conexión
psql -U postgres -d qa_bit_demo -c "SELECT 1"
```

### 2. Configurar Variables de Entorno

Editar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/qa_bit_demo
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=none
spring.flyway.enabled=true
```

### 3. Ejecutar la Aplicación

```bash
./gradlew bootRun
```

La aplicación estará disponible en `http://localhost:8080`

### 4. Acceder a Swagger UI

```
http://localhost:8080/swagger-ui.html
```

## 🔐 Autenticación

El proyecto usa **HTTP Basic Authentication** con credenciales predeterminadas:

**Admin:**
- Usuario: `admin`
- Contraseña: `admin`

**Endpoints Públicos (sin autenticación):**
- `GET /rooms` - Listar salas
- `GET /bookings` - Listar reservas
- `GET /swagger-ui.html` - Documentación

**Endpoints Protegidos (requieren autenticación):**
- `POST /rooms` - Crear sala
- `POST /bookings` - Crear reserva
- `DELETE /bookings/{id}` - Cancelar reserva

## 📡 Endpoints Principales

### Salas

#### Listar salas
```bash
curl http://localhost:8080/rooms
```

#### Crear sala (requiere autenticación)
```bash
curl -X POST http://localhost:8080/rooms \
  -H "Authorization: Basic $(echo -n 'admin:admin' | base64)" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Sala de Conferencias A",
    "description": "Sala para 20 personas"
  }'
```

### Reservas

#### Listar reservas
```bash
curl http://localhost:8080/bookings
```

#### Crear reserva (requiere autenticación)
```bash
curl -X POST http://localhost:8080/bookings \
  -H "Authorization: Basic $(echo -n 'admin:admin' | base64)" \
  -H "Content-Type: application/json" \
  -d '{
    "roomId": "uuid-de-sala",
    "responsable": "Juan Pérez",
    "dateStart": "2026-12-01T09:00:00",
    "dateEnd": "2026-12-01T11:00:00"
  }'
```

## 🏗️ Decisiones Arquitectónicas

### 1. Arquitectura Limpia (Clean Architecture)

El proyecto está dividido en tres capas:

```
com.omar26begue.demo/
├── domain/              # Lógica de negocio pura
│   ├── entities/        # Objetos de dominio (BookingDomain, RoomDomain)
│   ├── repositories/    # Interfaces de persistencia
│   └── exceptions/      # Excepciones de dominio
├── application/         # Casos de uso y orquestación
│   ├── usecase/        # Lógica de aplicación
│   ├── command/        # Objetos de comando (DTO)
│   └── query/          # Objetos de consulta
└── infrastructure/      # Detalles técnicos
    ├── jpa/            # Repositorios implementados con Spring Data JPA
    ├── rest/           # Controladores REST
    └── config/         # Configuración (Seguridad, OpenAPI)
```

**Ventajas:**
- Independencia de frameworks: Lógica de dominio sin dependencias de Spring
- Testabilidad: Fácil crear mocks de repositorios
- Escalabilidad: Clara separación de responsabilidades

### 2. Flyway para Migraciones de Base de Datos

- **V1__Create_rooms_table.sql**: Tabla de salas
- **V2__Create_bookings_table.sql**: Tabla de reservas
- **V3__Insert_fake_data.sql**: 20 salas + 30 reservas
- **V4__Insert_more_bookings_per_room.sql**: 90 reservas adicionales para pruebas

**Ventajas:**
- Versionado de esquema: Cada cambio es una migración
- Reproducibilidad: Mismo estado en dev, staging, producción
- Automatización: Flyway ejecuta migraciones al iniciar la aplicación

### 3. Validación de Disponibilidad de Salas

**Algoritmo de detección de conflictos:**

Dos reservas se solapan si:
```java
dateStart1 < dateEnd2 AND dateEnd1 > dateStart2
```

**Implementación:**
```java
// En BookingRepository.findConflictingBookings()
SELECT b FROM BookingDAO b 
WHERE b.room.id = :roomId 
AND b.cancelled = false 
AND ((b.dateStart < :dateEnd AND b.dateEnd > :dateStart))
```

**En BookingCreatedUseCase:**
1. Validar que `dateEnd > dateStart`
2. Verificar disponibilidad: consultar reservas conflictivas
3. Lanzar `ExTooManyException` si existen conflictos
4. Guardar la reserva

### 4. HTTP Basic Authentication con Spring Security

**Configuración:**
- BCryptPasswordEncoder para contraseñas
- InMemoryUserDetailsManager con usuarios predefinidos
- Request matchers lambdas para control granular por método HTTP

```java
requestMatchers(request -> "POST".equals(request.getMethod()) && 
                request.getRequestURI().startsWith("/bookings"))
  .authenticated()
```

**Ventajas:**
- Simple de implementar sin dependencias externas
- Adecuado para APIs internas

**Limitaciones (ver sección "Con Más Tiempo"):**
- Credenciales en memoria no escalables
- Sin refresh tokens
- Sin roles complejos

### 5. OpenAPI 3 / Swagger

- Documentación automática de endpoints
- SecurityScheme HTTP Basic configurado
- Esquema de respuestas documentado
- Accesible en `/swagger-ui.html` sin autenticación

## 🧪 Testing

Tres test files creados:

### 1. `RoomListUseCaseTest.java` (Unit Test)
- Prueba caso de éxito: retorna lista de 2 salas
- Prueba lista vacía
- Prueba manejo de excepciones
- Usa Mockito para mock de dependencias

### 2. `BookingCreatedUseCaseTest.java` (Unit Test)
- Prueba creación de reserva exitosa
- Prueba validación de rango de fechas (dateEnd debe ser > dateStart)
- Prueba cuando sala no existe
- Verifica interacciones con repositorios

### 3. `RoomControllerIntegrationTest.java` (Integration Test con MockMvc)
- GET /rooms público (sin autenticación)
- POST /rooms con credenciales válidas (admin:admin)
- POST /rooms rechazado sin autenticación (401)
- POST /rooms rechazado con credenciales inválidas (401)
- Acceso a Swagger sin autenticación

### 4. `BookingControllerIntegrationTest.java` (Integration Test con MockMvc)
- GET /bookings público
- POST /bookings con autenticación exitosa
- POST /bookings rechazado sin autenticación (401)
- Validación de rango de fechas (retorna 400 si dateEnd < dateStart)
- DELETE /bookings sin autenticación (401)
- DELETE /bookings con autenticación (200)

**Ejecutar tests:**
```bash
./gradlew test
```

## 📊 Base de Datos

### Migraciones

**V1 - Tabla Rooms:**
- `id_room` (UUID, PK)
- `name` (VARCHAR 255, indexed)
- `description` (TEXT)
- `created_at`, `updated_at` (TIMESTAMP)

**V2 - Tabla Bookings:**
- `id_booking` (UUID, PK)
- `id_room` (UUID, FK a rooms)
- `responsable` (VARCHAR 255)
- `date_start`, `date_end` (TIMESTAMP)
- `cancelled` (BOOLEAN, indexed)
- `created_at` (TIMESTAMP)
- Índices: (id_room), (date range), (cancelled)

**V3 & V4 - Datos de Prueba:**
- 20 salas
- 120+ reservas (mix de activas y canceladas)
- Datos distribuidos para probar conflictos de disponibilidad

## 🔄 Flujo de Creación de Reserva

```
POST /bookings
  ↓
BookingController.create()
  ↓
BookingCreatedUseCase.execute()
  ├─ RoomRepository.findId()          // Obtener sala
  ├─ validateDateRange()              // dateEnd > dateStart
  ├─ verifyRoomAvailability()         // Buscar conflictos
  │  └─ BookingRepository.findConflictingBookings()
  ├─ BookingRepository.save()         // Guardar si disponible
  └─ Mapear a BookingResponse
  ↓
HTTP 200 + BookingResponse
```


## 📚 Referencias

- [Spring Boot 3 Docs](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [OpenAPI 3 / Springdoc](https://springdoc.org/)
- [Flyway Migrations](https://flywaydb.org/)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

---

**Autor:** Omar Isalgué Begué  
**Fecha:** 2026-11-18  
**Estado:** MVP completado con tests e integración
