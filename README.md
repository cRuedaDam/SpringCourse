# 🧩 Microservicios con Spring Cloud y Spring Boot 2024

Este proyecto es el resultado práctico del curso **Microservicios con Spring Cloud y Spring Boot 2024**. Aquí aplico patrones modernos de desarrollo para crear un sistema distribuido y escalable, con comunicación entre microservicios, configuración centralizada, resiliencia, trazabilidad, seguridad y despliegue con Docker.

---

## 📚 Conocimientos aplicados en este proyecto

Durante la realización de este curso he dominado las siguientes tecnologías y conceptos:

✅ Desarrollo de microservicios con **Spring Boot**  
✅ Registro y descubrimiento con **Eureka (Spring Cloud Netflix)**  
✅ Configuración centralizada con **Spring Cloud Config Server**  
✅ Comunicación entre microservicios con **OpenFeign**  
✅ API Gateway con **Spring Cloud Gateway**  
✅ Manejo de fallos con **Circuit Breaker (Resilience4j)**  
✅ Eventos y mensajería con **Spring Cloud Stream y Kafka**  
✅ Seguridad con **OAuth2 y JWT**  
✅ Observabilidad con **OpenTelemetry, Prometheus, Grafana y Tempo**  
✅ Despliegue con **Docker y Docker Compose**  
✅ Implementación de **DDD (Domain-Driven Design)** para organización de servicios

---

## 🧱 Arquitectura del proyecto

```
.
├── companies_crud/               # Servicio principal CRUD para empresas
├── companies-crud-fallback/      # Servicio de fallback para pruebas de resiliencia
├── config-server/                # Configuración centralizada
├── docker-compose.yml            # Orquestación completa del entorno
├── geteway/                      # API Gateway con filtros, seguridad y rutas
├── metric_files/                 # Configuración de métricas y trazabilidad
│   ├── grafana-datasources.yml
│   ├── otel-collector.yml
│   ├── prometheus.yml
│   └── tempo.yml
├── registry-server/              # Eureka Server para descubrimiento de servicios
├── report-listener/              # Listener para eventos (Kafka)
├── report-ms/                    # Servicio de generación de reportes
├── sql/                          # Scripts para crear y poblar la base de datos
│   ├── create_schema.sql
│   └── data.sql
└── README.md                     # Este archivo
```

## 🛠 Stack tecnológico implementado

| Tecnología | Propósito |
|------------|-----------|
| **Java 17** | Lenguaje de programación |
| **Spring Boot 3** | Framework base para microservicios |
| **Spring Cloud** | Infraestructura de microservicios |
| **Eureka** | Registro y descubrimiento de servicios |
| **Config Server** | Configuración centralizada vía Git |
| **OpenFeign** | Comunicación HTTP entre microservicios |
| **Resilience4j** | Circuit Breaker para resiliencia |
| **Spring Cloud Gateway** | API Gateway con filtros y seguridad |
| **OAuth2 / JWT** | Seguridad y autenticación |
| **Docker** | Contenerización y despliegue |
| **Kafka** | Comunicación basada en eventos |
| **Prometheus** | Recolección de métricas |
| **Grafana** | Visualización de métricas |
| **Tempo** | Trazabilidad distribuida |
| **OpenTelemetry** | Instrumentación y monitoreo |

---

## 🚀 Guía de instalación y ejecución

### Prerrequisitos
- Docker & Docker Compose instalados
- Java 17+ (para desarrollo local)
- Maven (opcional para compilación local)

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/microservicios-spring-boot-2024.git
cd microservicios-spring-boot-2024
```

### 2. Compilar proyectos (opcional)
```bash
./mvnw clean install -DskipTests
```

### 3. Levantar el ecosistema completo
```bash
docker-compose up --build
```

### 4. Verificar servicios activos

| Servicio | URL | Credenciales |
|----------|-----|--------------|
| **Eureka Server** | http://localhost:8761 | - |
| **API Gateway** | http://localhost:8080 | - |
| **Grafana** | http://localhost:3000 | admin/admin |
| **Prometheus** | http://localhost:9090 | - |
| **Tempo** | http://localhost:3200 | - |

---

## 📡 API Endpoints

Todas las peticiones se realizan a través del **API Gateway** en el puerto 8080:

```bash
# Gestión de empresas
GET    http://localhost:8080/companies
POST   http://localhost:8080/companies
PUT    http://localhost:8080/companies/{id}
DELETE http://localhost:8080/companies/{id}

# Generación de reportes
GET    http://localhost:8080/reports
POST   http://localhost:8080/reports
```

### Ejemplo de uso
```bash
# Obtener todas las empresas
curl -X GET http://localhost:8080/companies

# Crear nueva empresa
curl -X POST http://localhost:8080/companies \
  -H "Content-Type: application/json" \
  -d '{"name":"Tech Corp","category":"Technology"}'
```

---

## 🧪 Testing y monitoreo

### Herramientas de prueba
- **Postman**: Colección de endpoints disponible
- **cURL**: Ejemplos incluidos en documentación
- **Swagger UI**: Accesible vía API Gateway (si habilitado)

### Observabilidad implementada
- **Métricas**: Prometheus + Grafana dashboards
- **Trazabilidad**: OpenTelemetry + Tempo
- **Logging**: Structured logging con correlación de requests
- **Health checks**: Spring Actuator en todos los servicios

---

## 🏗️ Patrones y principios aplicados

### Arquitectura de Microservicios
- **Service Registry**: Eureka para descubrimiento automático
- **API Gateway**: Punto único de entrada con enrutamiento inteligente
- **Configuration Management**: Externalización vía Config Server
- **Circuit Breaker**: Tolerancia a fallos con Resilience4j

### Domain-Driven Design (DDD)
- Separación clara de dominios de negocio
- Agregados y entidades bien definidos
- Servicios de dominio especializados
- Anti-corruption layers entre contextos

### Event-Driven Architecture
- Comunicación asíncrona con Kafka
- Event sourcing para auditoría
- Saga pattern para transacciones distribuidas

---

## 🔧 Configuración avanzada

### Variables de entorno clave
```env
SPRING_PROFILES_ACTIVE=docker
CONFIG_SERVER_URL=http://config-server:8888
EUREKA_SERVER_URL=http://registry-server:8761/eureka
KAFKA_BOOTSTRAP_SERVERS=kafka:9092
```

### Escalabilidad
- Servicios preparados para scaling horizontal
- Load balancing automático vía Eureka
- Base de datos distribuida con particionamiento

---

## 📊 Métricas y dashboards

He implementado dashboards completos en Grafana para monitorear:

- **Performance**: Latencia, throughput, errores
- **Business metrics**: Transacciones, usuarios activos
- **Infrastructure**: CPU, memoria, conexiones DB
- **Distributed tracing**: Request flows entre servicios

---

## 🎯 Resultados del aprendizaje

Este proyecto demuestra mi dominio en:

1. **Arquitectura de sistemas distribuidos**
2. **Implementación de patrones de microservicios**
3. **DevOps y containerización**
4. **Observabilidad y monitoreo**
5. **Seguridad en aplicaciones distribuidas**
6. **Testing de sistemas complejos**

---

## 📅 Información del curso

**Plataforma**: Udemy  
**Curso**: Microservicios con Spring Cloud y Spring Boot 2024  
**Completado**: Julio 2025  
**Estado**: ✅ Certificado obtenido

---

## 🤝 Contribuciones

Este proyecto refleja mi aprendizaje personal, pero estoy abierto a:
- Code reviews y feedback
- Mejoras en la arquitectura
- Nuevas features y patrones
- Documentación adicional

---

## 📄 Licencia

Este proyecto fue desarrollado con fines educativos como parte del curso de Udemy mencionado.

---

⭐ **Si este proyecto te resulta útil, no olvides darle una estrella!**