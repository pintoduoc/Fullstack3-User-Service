-- liquibase formatted sql

-- changeset benja:3
INSERT INTO brigada (nombre, sector) VALUES
                                         ('Brigada Alfa', 'Norte'),
                                         ('Brigada Bravo', 'Sur'),
                                         ('Brigada Charlie', 'Este');

-- changeset benja:4
INSERT INTO usuario (rut, nombre_completo, contacto, rol, brigada_id) VALUES
                                                                          ('11111111-1', 'Juan Perez', 'juan@gmail.com', 'CIUDADANO', NULL),
                                                                          ('22222222-2', 'Maria Gonzalez', 'maria@gmail.com', 'CIUDADANO', NULL),
                                                                          ('33333333-3', 'Pedro Soto', 'pedro@gmail.com', 'BRIGADISTA', 1),
                                                                          ('44444444-4', 'Ana Silva', 'ana@gmail.com', 'BRIGADISTA', 1),
                                                                          ('55555555-5', 'Luis Tapia', 'luis@gmail.com', 'BRIGADISTA', 2),
                                                                          ('66666666-6', 'Carmen Rojas', 'carmen@gmail.com', 'ADMINISTRADOR', NULL),
                                                                          ('77777777-7', 'Diego Vargas', 'diego@gmail.com', 'BRIGADISTA', 3),
                                                                          ('88888888-8', 'Camila Castro', 'camila@gmail.com', 'CIUDADANO', NULL),
                                                                          ('99999999-9', 'Javier Morales', 'javier@gmail.com', 'BRIGADISTA', 2),
                                                                          ('10101010-0', 'Sofia Reyes', 'sofia@gmail.com', 'BRIGADISTA', 3);