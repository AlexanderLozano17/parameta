# 🚀 Prueba Técnica Developer - Parameta S.A.S

Este repositorio contiene la implementación de una prueba técnica solicitada por **Parameta S.A.S**, que consiste en desarrollar un servicio REST utilizando **Java 17** y **Spring Boot**, con validaciones de negocio, persistencia en base de datos y comunicación con un servicio SOAP externo.

---

## 🧩 Descripción del Proyecto

El proyecto implementa un servicio REST que recibe los atributos de un objeto `Empleado` vía método `GET`, realiza las validaciones necesarias y, en caso exitoso, comunica los datos a un servicio SOAP para su almacenamiento en una base de datos PostgreSQL. Finalmente, retorna un JSON con la información del empleado, incluyendo:

- Edad actual (años, meses y días)
- Tiempo de vinculación a la compañía (años, meses y días)

---

## 🧾 Atributos del Empleado

- `nombres`: `String`
- `apellidos`: `String`
- `tipoDocumento`: `String`
- `numeroDocumento`: `String`
- `fechaNacimiento`: `Date`
- `fechaVinculacion`: `Date`
- `cargo`: `String`
- `salario`: `Double`

---

## ✅ Validaciones

- Formato válido de fechas
- Campos obligatorios no nulos
- Validación de mayoría de edad (≥ 18 años)

---

## 🛠️ Tecnologías y Herramientas

| Tecnología       | Versión      | Descripción                                    |
|------------------|--------------|------------------------------------------------|
| Java             | 17           | Lenguaje principal                             |
| Spring Boot      | 3.x          | Framework principal de backend                 |
| Spring JPA       |              | Persistencia de datos                          |
| Flyway           |              | Migración y versionamiento de base de datos    |
| PostgreSQL       | 15+          | Motor de base de datos relacional              |
| Docker           |              | Contenedores                                   |
| Docker Compose   |              | Orquestación de contenedores                   |
| SOAP Client      |              | Comunicación con servicio web SOAP externo     |
| OpenAPI / Swagger|              | Documentación de la API REST                   |

---

## 🧪 Ejecución Local

1. **Clona el repositorio:**

```bash
git clone https://github.com/tu-usuario/nombre-del-repo.git
cd nombre-del-repo
