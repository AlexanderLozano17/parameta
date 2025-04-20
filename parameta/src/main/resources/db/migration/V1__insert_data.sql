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
INSERT INTO person (id, names, last_names, type_document_id, dni, date_of_birth)
VALUES (1, 'Juan', 'Pérez', 1, '100000001', '1990-01-01');

INSERT INTO person (id, names, last_names, type_document_id, dni, date_of_birth)
VALUES (2, 'María', 'González', 1, '100000002', '1988-03-15');

INSERT INTO person (id, names, last_names, type_document_id, dni, date_of_birth)
VALUES (3, 'Carlos', 'Ramírez', 2, '100000003', '1995-07-22');

INSERT INTO person (id, names, last_names, type_document_id, dni, date_of_birth)
VALUES (4, 'Luisa', 'Martínez', 3, '100000004', '1992-11-10');

INSERT INTO person (id, names, last_names, type_document_id, dni, date_of_birth)
VALUES (5, 'Andrés', 'López', 1, '100000005', '1985-06-05');

