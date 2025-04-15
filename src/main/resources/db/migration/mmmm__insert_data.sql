-- Esto solo es para verificar que la migración se ejecutó
SELECT 'Migración ejecutada correctamente' AS mensaje;

-- INSERTAR ROLES
INSERT INTO role (id, rol) VALUES (1, 'Desarrollador');
INSERT INTO role (id, rol) VALUES (2, 'Analista');
INSERT INTO role (id, rol) VALUES (3, 'Tester');
INSERT INTO role (id, rol) VALUES (4, 'Scrum Master');
INSERT INTO role (id, rol) VALUES (5, 'DevOps');

-- INSERTAR TIPOS DE DOCUMENTO
INSERT INTO type_document (id, code, document) VALUES (1, 'CC', 'Cédula de Ciudadanía');
INSERT INTO type_document (id, code, document) VALUES (2, 'TI', 'Tarjeta de Identidad');
INSERT INTO type_document (id, code, document) VALUES (3, 'CE', 'Cédula de Extranjería');

-- INSERTAR PERSONAS
INSERT INTO person (id, names, lastames, type_document_id, dni, date_of_birth)
VALUES (1, 'Juan', 'Pérez', 1, '100000001', '1990-01-01');

INSERT INTO person (id, names, lastames, type_document_id, dni, date_of_birth)
VALUES (2, 'María', 'González', 1, '100000002', '1988-03-15');

INSERT INTO person (id, names, lastames, type_document_id, dni, date_of_birth)
VALUES (3, 'Carlos', 'Ramírez', 2, '100000003', '1995-07-22');

INSERT INTO person (id, names, lastames, type_document_id, dni, date_of_birth)
VALUES (4, 'Luisa', 'Martínez', 3, '100000004', '1992-11-10');

INSERT INTO person (id, names, lastames, type_document_id, dni, date_of_birth)
VALUES (5, 'Andrés', 'López', 1, '100000005', '1985-06-05');

-- INSERTAR EMPLEADO
INSERT INTO employee (id, person_id, role_id, date_vinculation, salary)
VALUES (1, 1, 1, '2023-05-01', 3600.0);

INSERT INTO employee (id, person_id, role_id, date_vinculation, salary)
VALUES (2, 2, 2, '2022-09-10', 3300.0);

INSERT INTO employee (id, person_id, role_id, date_vinculation, salary)
VALUES (3, 3, 3, '2024-01-15', 3100.0);

INSERT INTO employee (id, person_id, role_id, date_vinculation, salary)
VALUES (4, 4, 4, '2021-12-20', 4100.0);

INSERT INTO employee (id, person_id, role_id, date_vinculation, salary)
VALUES (5, 5, 5, '2020-07-01', 4300.0);

INSERT INTO employee (id, person_id, role_id, date_vinculation, salary)
VALUES (6, 1, 2, '2024-02-10', 3400.0); 

INSERT INTO employee (id, person_id, role_id, date_vinculation, salary)
VALUES (7, 2, 3, '2023-08-01', 3000.0); 
