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


## ⚙️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation (`@Valid`)
- Docker & Docker Compose
- PostgreSQL
- OpenAPI / Swagger
- Consumo de servicios SOAP
- Lombok

# Explicación de la solución

Este proyecto contiene dos microservicios que interactúan a través de un servicio SOAP:

1. **ws-soap - Exposición del servicio SOAP**: Este microservicio expone un servicio SOAP.
2. **parameta - Consumo del servicio SOAP**: Este microservicio consume el servicio SOAP expuesto por el primer ws-soap.

## Estructura del Proyecto

### Microservicio ws-soap: Servicio SOAP

Este microservicio expone un servicio SOAP que proporciona funcionalidad relacionada con el registro de empleados. Los detalles del servicio están definidos en el archivo WSDL y el servicio está implementado utilizando Spring Boot y Spring Web Services.

- **Paquete:** `com.soap`
- **Puntos clave:**
  - Exposición del servicio SOAP a través de un endpoint.
  - Validación de solicitudes y manejo de excepciones.
  - Persistencia de datos a través de JPA y base de datos.

### Microservicio 2 parameta: Cliente SOAP

Este microservicio consume el servicio SOAP expuesto por el Microservicio ws-soap. Se comunica con el microservicio que expone el servicio SOAP utilizando un cliente SOAP generado a partir del WSDL.

- **Paquete:** `parameta.demo.parameta`
- **Puntos clave:**
  - Generación del cliente SOAP a partir del archivo WSDL.
  - Llamada al servicio SOAP utilizando el cliente generado.
  - Manejo de respuestas y errores.


## 🏗️ Arquitectura y patrones implementados

### 🧱 Patrón por Capas (Layered Architecture)
Separación clara de responsabilidades:

El proyecto sigue una arquitectura por capas, promoviendo la separación de responsabilidades y el mantenimiento del código. A continuación, se describen los componentes principales:

- **Controller:** Maneja las solicitudes y respuestas HTTP. Es la capa de entrada al sistema, delegando la lógica de negocio a los servicios.

- **Service:** Define la interfaz de los servicios. Representa la abstracción de las operaciones del negocio.

- **ServiceImpl:** Implementación concreta de los servicios. Contiene la lógica de negocio y coordina las operaciones entre los diferentes componentes.

- **Repository:** Capa encargada del acceso a datos. Utiliza Spring Data JPA para facilitar la comunicación con la base de datos.

- **Entities:** Representan el modelo de datos de la aplicación. Estas clases están mapeadas a las tablas de la base de datos utilizando anotaciones JPA.

- **DTOs (Data Transfer Objects):** Objetos de transferencia de datos. Permiten encapsular y transportar datos entre capas, desacoplando la representación interna de las entidades del modelo de dominio.


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
  

## 🚀 Cómo ejecutar

## Ejecución con Docker Compose

Para ejecutar el proyecto utilizando Docker Compose, sigue estos pasos:

1. **Clona el repositorio**:
   Si aún no has clonado el repositorio, ejecuta el siguiente comando:

```bash
https://github.com/AlexanderLozano17/parameta.git
```

2. **Verifica la configuración de Docker y Docker Compose: **:
   Asegúrate de tener Docker y Docker Compose instalados en tu máquina. Puedes verificar si están 	 instalados ejecutando los siguientes comandos::

```bash
docker --version
docker-compose --version
```

3. **Levanta los servicios con Docker Compose: **:
   Asegúrate de tener Docker y Docker Compose instalados en tu máquina. Puedes verificar si están 	 instalados ejecutando los siguientes comandos::

```bash
docker-compose up --build
```

## Datos Iniciales

Dentro del microservicio `parameta`, se encuentra el archivo:

- `src/main/resources/db/migration/V1__data.insert.sql`

Este archivo contiene datos de prueba iniciales que pueden ser utilizados para pobar la base de datos de forma manual.

> ⚠️ Si deseas ejecutar este script, debes aplicarlo directamente