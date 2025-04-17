CREATE TABLE productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    precio DOUBLE,
    negocio_id BIGINT NOT NULL,
    estimado_id BIGINT NOT NULL,
    descripcion TEXT,
    fechaDeCreacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_productos_negocios FOREIGN KEY (negocio_id) REFERENCES negocios(id),
    CONSTRAINT fk_productos_estimados FOREIGN KEY (estimado_id) REFERENCES estimados(id)
);
