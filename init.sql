-- Creación de tablas
CREATE TABLE IF NOT EXISTS persona (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(10),
    edad INT,
    identificacion VARCHAR(20) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT PRIMARY KEY,
    cliente_id VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL,
    FOREIGN KEY (id) REFERENCES persona(id)
);

CREATE TABLE IF NOT EXISTS cuenta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(50),
    saldo_inicial DECIMAL(15,2),
    estado BOOLEAN NOT NULL,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP,
    tipo_movimiento VARCHAR(50),
    valor DECIMAL(15,2),
    saldo DECIMAL(15,2),
    cuenta_id BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);

INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Jose Lema', 'M', 30, '1234567890', 'Otavalo sn y principal', '098254785') AS new_data
ON DUPLICATE KEY UPDATE
nombre = new_data.nombre, genero = new_data.genero, edad = new_data.edad, 
direccion = new_data.direccion, telefono = new_data.telefono;

INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Marianela Montalvo', 'F', 28, '0987654321', 'Amazonas y NNUU', '097548965') AS new_data
ON DUPLICATE KEY UPDATE
nombre = new_data.nombre, genero = new_data.genero, edad = new_data.edad, 
direccion = new_data.direccion, telefono = new_data.telefono;

INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Juan Osorio', 'M', 35, '1122334455', '13 junio y Equinoccial', '098874587') AS new_data
ON DUPLICATE KEY UPDATE
nombre = new_data.nombre, genero = new_data.genero, edad = new_data.edad, 
direccion = new_data.direccion, telefono = new_data.telefono;

INSERT INTO cliente (id, cliente_id, contrasena, estado)
VALUES (1, 'joselema', '1234', true) AS new_data
ON DUPLICATE KEY UPDATE
contrasena = new_data.contrasena, estado = new_data.estado;

INSERT INTO cliente (id, cliente_id, contrasena, estado)
VALUES (2, 'marianamontalvo', '5678', true) AS new_data
ON DUPLICATE KEY UPDATE
contrasena = new_data.contrasena, estado = new_data.estado;

INSERT INTO cliente (id, cliente_id, contrasena, estado)
VALUES (3, 'juanosorio', '1245', true) AS new_data
ON DUPLICATE KEY UPDATE
contrasena = new_data.contrasena, estado = new_data.estado;

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES ('478758', 'Ahorro', 2000, true, 1) AS new_data
ON DUPLICATE KEY UPDATE
tipo_cuenta = new_data.tipo_cuenta, saldo_inicial = new_data.saldo_inicial, estado = new_data.estado, cliente_id = new_data.cliente_id;

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES ('225487', 'Corriente', 100, true, 2) AS new_data
ON DUPLICATE KEY UPDATE
tipo_cuenta = new_data.tipo_cuenta, saldo_inicial = new_data.saldo_inicial, estado = new_data.estado, cliente_id = new_data.cliente_id;

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES ('495878', 'Ahorros', 0, true, 3) AS new_data
ON DUPLICATE KEY UPDATE
tipo_cuenta = new_data.tipo_cuenta, saldo_inicial = new_data.saldo_inicial, estado = new_data.estado, cliente_id = new_data.cliente_id;

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES ('496825', 'Ahorros', 540, true, 2) AS new_data
ON DUPLICATE KEY UPDATE
tipo_cuenta = new_data.tipo_cuenta, saldo_inicial = new_data.saldo_inicial, estado = new_data.estado, cliente_id = new_data.cliente_id;

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES ('585545', 'Corriente', 1000, true, 1) AS new_data
ON DUPLICATE KEY UPDATE
tipo_cuenta = new_data.tipo_cuenta, saldo_inicial = new_data.saldo_inicial, estado = new_data.estado, cliente_id = new_data.cliente_id;

INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES ('2022-02-10 10:00:00', 'Retiro', -575, 1425, 1) AS new_data
ON DUPLICATE KEY UPDATE
tipo_movimiento = new_data.tipo_movimiento, valor = new_data.valor, saldo = new_data.saldo, cuenta_id = new_data.cuenta_id;

INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES ('2022-02-08 12:00:00', 'Deposito', 600, 700, 2) AS new_data
ON DUPLICATE KEY UPDATE
tipo_movimiento = new_data.tipo_movimiento, valor = new_data.valor, saldo = new_data.saldo, cuenta_id = new_data.cuenta_id;

INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES ('2022-02-09 14:00:00', 'Deposito', 150, 150, 3) AS new_data
ON DUPLICATE KEY UPDATE
tipo_movimiento = new_data.tipo_movimiento, valor = new_data.valor, saldo = new_data.saldo, cuenta_id = new_data.cuenta_id;

INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES ('2022-02-07 16:00:00', 'Retiro', -540, 0, 4) AS new_data
ON DUPLICATE KEY UPDATE
tipo_movimiento = new_data.tipo_movimiento, valor = new_data.valor, saldo = new_data.saldo, cuenta_id = new_data.cuenta_id;
