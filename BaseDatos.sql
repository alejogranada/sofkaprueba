-- Creación de tablas
CREATE TABLE persona (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(10),
    edad INT,
    identificacion VARCHAR(20) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE cliente (
    id BIGINT PRIMARY KEY,
    cliente_id VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL,
    FOREIGN KEY (id) REFERENCES persona(id)
);

CREATE TABLE cuenta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(50),
    saldo_inicial DECIMAL(15,2),
    estado BOOLEAN NOT NULL,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP,
    tipo_movimiento VARCHAR(50),
    valor DECIMAL(15,2),
    saldo DECIMAL(15,2),
    cuenta_id BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);

-- Inserción de datos de ejemplo
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono) VALUES
('Jose Lema', 'M', 30, '1234567890', 'Otavalo sn y principal', '098254785'),
('Marianela Montalvo', 'F', 28, '0987654321', 'Amazonas y NNUU', '097548965'),
('Juan Osorio', 'M', 35, '1122334455', '13 junio y Equinoccial', '098874587');

-- Nota: Asegúrate de que los IDs coincidan con los registros insertados en 'persona'
INSERT INTO cliente (id, cliente_id, contrasena, estado) VALUES
(1, 'joselema', '1234', true),
(2, 'marianamontalvo', '5678', true),
(3, 'juanosorio', '1245', true);

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id) VALUES
('478758', 'Ahorro', 2000, true, 1),
('225487', 'Corriente', 100, true, 2),
('495878', 'Ahorros', 0, true, 3),
('496825', 'Ahorros', 540, true, 2),
('585545', 'Corriente', 1000, true, 1);

INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id) VALUES
('2022-02-10 10:00:00', 'Retiro', -575, 1425, 1),
('2022-02-08 12:00:00', 'Deposito', 600, 700, 2),
('2022-02-09 14:00:00', 'Deposito', 150, 150, 3),
('2022-02-07 16:00:00', 'Retiro', -540, 0, 4);