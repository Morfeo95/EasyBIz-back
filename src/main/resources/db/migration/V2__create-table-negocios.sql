CREATE TABLE negocios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    name VARCHAR(100),
    urlPhoto VARCHAR(255),
    productos JSON,
    fechaDeCreacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_negocios_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
