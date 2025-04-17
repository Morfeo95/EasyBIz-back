CREATE TABLE estimados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    moneda VARCHAR(50),
    nombre VARCHAR(100),
    unidadesProducidas INT,
    plazo INT,
    ganancia INT,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    insumos JSON,
    gastosPlanta JSON,
    gastosDiarios JSON,
    CONSTRAINT fk_estimados_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
