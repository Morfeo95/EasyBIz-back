ALTER TABLE usuarios CHANGE fechaDeCreacion fecha_de_creacion DATETIME;
ALTER TABLE negocios CHANGE fechaDeCreacion fecha_de_creacion DATETIME;
ALTER TABLE negocios CHANGE urlPhoto url_photo VARCHAR(255);
ALTER TABLE estimados CHANGE unidadesProducidas unidades_producidas INT;
ALTER TABLE estimados CHANGE gastosPlanta gastos_planta JSON;
ALTER TABLE estimados CHANGE gastosDiarios gastos_diarios JSON;
ALTER TABLE productos CHANGE fechaDeCreacion fecha_de_creacion DATETIME;