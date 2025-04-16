# 📦 Prueba Técnica Developer - Parameta S.A.S

Este proyecto es una implementación de un servicio REST desarrollado en **Java 17** utilizando **Spring Boot**. El objetivo es recibir información de un empleado, validar los datos, consultar un servicio SOAP y almacenar los datos en una base de datos. Finalmente, retorna un JSON con información calculada adicional.

---

## 🧩 Descripción del problema

Se requiere exponer un servicio REST que reciba un objeto **Empleado** con los siguientes atributos:

- Nombres (`String`)
- Apellidos (`String`)
- Tipo de Documento (`String`)
- Número de Documento (`String`)
- Fecha de Nacimiento (`Date`)
- Fecha de Vinculación a la Compañía (`Date`)
- Cargo (`String`)
- Salario (`Double`)

### Requisitos funcionales:

- Validar que los campos no vengan vacíos y que las fechas estén en formato válido.
- Validar que el empleado sea **mayor de edad**.
- Invocar un servicio **SOAP** con la información del empleado.
- Almacenar los datos en una base de datos **PostgreSQL**.
- El servicio REST debe responder con un JSON que incluya:
  - Edad actual del empleado (**años, meses y días**).
  - Tiempo de vinculación a la empresa (**años, meses y días**).

---

## ⚙️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation (`@Valid`)
- Flyway (para versionamiento de la base de datos)
- Docker & Docker Compose
- PostgreSQL
- OpenAPI / Swagger
- Consumo de servicios SOAP
- Lombok

---

## 🏗️ Arquitectura y patrones implementados

### 🧱 Patrón por Capas (Layered Architecture)
Separación clara de responsabilidades:

- **Controller:** Maneja la entrada/salida HTTP.
- **Service:** Contiene la lógica de negocio.
- **Repository:** Capa de acceso a datos con JPA.
- **DTOs:** Se utilizan para desacoplar la representación interna de las entidades.

### 🧰 Abstracción del acceso a datos

- Uso de interfaces `Repository` y `JpaRepository` de Spring Data JPA.
- Implementación de la lógica adicional en `ServiceImpl` (patrón Service).
- Separación entre entidades (`Entity`) y objetos de transferencia (`DTO`), fomentando el **principio de responsabilidad única** (SRP).

### 🔄 Conversión entre entidades y DTOs

- Mapeo manual o con ayuda de `ModelMapper` para separar claramente la lógica de negocio de la representación externa.
  
---

## 🚀 Cómo ejecutar

### 1. Clonar el repositorio

```bash
https://github.com/AlexanderLozano17/parameta.git
```

## 📚 Modelo de Datos

Este proyecto incluye un modelo de datos orientado a representar una estructura básica de empleados, personas, roles y tipos de documento.

### 🔗 Relaciones entre Entidades

- **`PersonEntity` — `TypeDocumentEntity`**  
  Relación de muchos a uno (`@ManyToOne`).  
  Muchas personas pueden tener el mismo tipo de documento.

- **`PersonEntity` — `EmployeeEntity`**  
  Relación uno a uno (`@OneToOne`).  
  Una persona puede estar asociada a un empleado.

- **`EmployeeEntity` — `RoleEntity`**  
  Relación de muchos a uno (`@ManyToOne`).  
  Muchos empleados pueden compartir un mismo rol.

- **`RoleEntity` — `EmployeeEntity`**  
  Relación inversa de la anterior (`@OneToMany`).  
  Un rol puede estar asociado a múltiples empleados.

---
