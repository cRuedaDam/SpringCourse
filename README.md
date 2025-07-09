# SpringCourse Project

Este proyecto es una arquitectura básica de microservicios usando Spring Boot y Docker.

## 🧭 Flujo de ejecución

Para usar correctamente este sistema, sigue los pasos en el siguiente orden:

1. **Levantar el contenedor de Docker**
   - Asegúrate de tener Docker instalado y corriendo.
   - Ejecuta:
     ```bash
     docker-compose up -d
     ```

2. **Iniciar el `registry-server`**
   - Desde la carpeta correspondiente, ejecuta:
     ```bash
     ./mvnw spring-boot:run
     ```

3. **Ejecutar el `config-server`**
   - Desde la carpeta correspondiente, ejecuta:
     ```bash
     ./mvnw spring-boot:run
     ```

4. **Ejecutar el microservicio `companies-crud`**
   - Desde la carpeta correspondiente, ejecuta:
     ```bash
     ./mvnw spring-boot:run
     ```

---

## 🧱 Estructura del proyecto

- `registry-server`: Servicio de descubrimiento (Eureka)
- `config-server`: Servidor de configuración centralizada
- `companies-crud`: Microservicio principal para gestión de empresas
- `docker-compose.yml`: Orquestación de contenedores necesarios (DB, Config, etc.)

---

## ✅ Requisitos

- Docker
- Java 17+
- Maven (o usar wrapper `./mvnw`)
- Git

---

## 📦 Instalación

Clona el repositorio:

```bash
git clone https://github.com/cRuedaDam/SpringCourse.git
cd SpringCourse
