# 🚀 Tareas API - Sistema de Gestión Relacional

Esta es una API REST profesional construida con **Java 21** y **Spring Boot 3**, diseñada bajo estándares de industria para la gestión de tareas vinculadas a usuarios.

## 🌍 Demo en Vivo
Puedes probar la API aquí: [Link a tu Swagger en Railway](https://tareas-java-production.up.railway.app/swagger-ui/index.html)
git 
## 🛠️ Stack Tecnológico
* **Lenguaje:** Java 21 (Uso de Records y Virtual Threads ready).
* **Framework:** Spring Boot 3.4.x.
* **Base de Datos:** PostgreSQL 15 (Dockerizado).
* **Seguridad:** Spring Security + BCrypt Password Hashing.
* **Mapeo:** MapStruct (Automatización de DTOs).
* **Documentación:** Swagger UI (OpenAPI 3).
* **Infraestructura:** Docker & Docker Compose.

## 🏗️ Arquitectura y Buenas Prácticas
El proyecto sigue una arquitectura de capas limpia para asegurar el mantenimiento a largo plazo:
* **Separación de Responsabilidades:** Controller -> Service (Interfaces) -> Repository.
* **Seguridad de Datos:** Uso de DTOs para evitar la sobreexposición de entidades.
* **Soft Delete:** Implementación de borrado lógico para integridad de auditoría.
* **JPA Auditing:** Registro automático de fechas de creación.
* **Global Exception Handling:** Manejo centralizado de errores con respuestas JSON estandarizadas.

## 🐳 Cómo correr el proyecto localmente
1. Clonar el repositorio.
2. Asegurarse de tener Docker abierto.
3. Ejecutar en la terminal:
   ```bash
   docker-compose up -d --build