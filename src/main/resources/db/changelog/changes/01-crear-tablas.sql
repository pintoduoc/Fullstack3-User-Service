-- liquibase formatted sql

-- changeset benja:1
CREATE TABLE brigada (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         sector VARCHAR(255) NOT NULL
);

-- changeset benja:2
CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         rut VARCHAR(20) NOT NULL,
                         nombre_completo VARCHAR(255) NOT NULL,
                         contacto VARCHAR(255),
                         rol VARCHAR(50) NOT NULL,
                         brigada_id BIGINT NULL,
                         CONSTRAINT fk_usuario_brigada FOREIGN KEY (brigada_id) REFERENCES brigada(id)
);