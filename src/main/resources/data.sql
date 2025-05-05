-- REGIONES, CIUDADES Y COMUNAS

-- Tabla regiones
INSERT INTO regiones (nombre, codigo, ordinal) VALUES 
('Región de Arica y Parinacota', 'XV', 1),
('Región de Tarapacá', 'I', 2),
('Región de Antofagasta', 'II', 3),
('Región de Atacama', 'III', 4),
('Región de Coquimbo', 'IV', 5),
('Región de Valparaíso', 'V', 6),
('Región Metropolitana de Santiago', 'RM', 7),
('Región del Libertador General Bernardo O''Higgins', 'VI', 8);

-- Tabla ciudades
INSERT INTO ciudades (nombre, region_id) VALUES 
('Santiago', 7),
('Viña del Mar', 6),
('Valparaíso', 6),
('Rancagua', 8),
('Arica', 1),
('Iquique', 2),
('Antofagasta', 3),
('La Serena', 5);

-- Tabla comunas
INSERT INTO comunas (nombre, ciudad_id) VALUES 
('Santiago', 1),
('Providencia', 1),
('Las Condes', 1),
('Viña del Mar', 2),
('Concón', 2),
('Valparaíso', 3),
('Rancagua', 4),
('Machalí', 4),
('Arica', 5),
('Iquique', 6);

-- Tabla rol
INSERT INTO rol (nombre, descripcion) VALUES 
('Admin', 'Administrador del sistema'),
('Cliente', 'Usuario cliente de la ferretería'),
('Vendedor', 'Vendedor de tienda de ferretería'),
('Bodeguero', 'Encargado de bodega de ferretería'),
('Despachador', 'Encargado de despachos de productos');

-- Tabla estado_pedido
INSERT INTO estado_pedido (nombre, descripcion) VALUES 
('Pendiente', 'Pedido ingresado pero no procesado'),
('Confirmado', 'Pedido confirmado para preparación'),
('En preparación', 'Pedido en preparación en bodega'),
('Listo para despacho', 'Pedido listo para ser despachado'),
('En despacho', 'Pedido en proceso de despacho'),
('Entregado', 'Pedido entregado al cliente'),
('Cancelado', 'Pedido cancelado');

-- Tabla estado_despacho
INSERT INTO estados_despacho (nombre, descripcion) VALUES 
('Pendiente', 'Despacho pendiente de asignación'),
('Asignado', 'Despacho asignado a transportista'),
('En ruta', 'Despacho en ruta de entrega'),
('Entregado', 'Despacho entregado al cliente'),
('Fallido', 'Intento de entrega fallido');

-- Tabla estado_pago
INSERT INTO estado_pago (nombre, descripcion) VALUES 
('Pendiente', 'Pago pendiente'),
('Procesando', 'Pago en procesamiento'),
('Completado', 'Pago completado'),
('Rechazado', 'Pago rechazado'),
('Reembolsado', 'Pago reembolsado');

-- Tabla metodo_pago
INSERT INTO metodo_pago (nombre, descripcion, activo) VALUES 
('Tarjeta de crédito', 'Pago con tarjeta de crédito', TRUE),
('Tarjeta de débito', 'Pago con tarjeta de débito', TRUE),
('Transferencia bancaria', 'Pago por transferencia bancaria', TRUE),
('Efectivo', 'Pago en efectivo en tienda', TRUE),
('WebPay', 'Pago vía WebPay', TRUE);

-- Tabla tipo_entrega
INSERT INTO tipo_entrega (nombre, descripcion) VALUES 
('Despacho a domicilio', 'Entrega en domicilio del cliente'),
('Retiro en tienda', 'Cliente retira en sucursal Ferramas'),
('Punto de entrega', 'Entrega en punto de retiro asociado'),
('Entrega express', 'Entrega urgente en menos de 24 horas'),
('Entrega programada', 'Entrega en fecha y hora programada');

-- Tabla tipo_movimiento_inventario
INSERT INTO tipo_movimiento (nombre, descripcion) VALUES 
('Ingreso', 'Ingreso de productos al inventario'),
('Salida', 'Salida de productos del inventario'),
('Ajuste positivo', 'Ajuste positivo de inventario'),
('Ajuste negativo', 'Ajuste negativo de inventario'),
('Traslado', 'Traslado entre sucursales Ferramas');

-- CATEGORÍA Y MARCA

-- Tabla categoria
INSERT INTO categoria (nombre, descripcion) VALUES
('Herramientas Manuales', 'Herramientas de uso manual para construcción y reparación'),
('Herramientas Eléctricas', 'Herramientas accionadas por energía eléctrica'),
('Materiales Construcción', 'Materiales básicos para construcción'),
('Ferretería General', 'Artículos diversos de ferretería'),
('Pinturas', 'Pinturas y accesorios para pintura'),
('Seguridad Industrial', 'Equipos de protección personal'),
('Jardinería', 'Herramientas y productos para jardín'),
('Plomería', 'Materiales y herramientas para plomería');

-- Tabla marca
INSERT INTO marca (nombre, descripcion) VALUES
('Bosch', 'Herramientas eléctricas profesionales alemanas'),
('Stanley', 'Herramientas manuales de alta calidad'),
('Tramontina', 'Herramientas y utensilios brasileños'),
('Sika', 'Productos químicos para construcción'),
('Anasac', 'Productos para jardinería y agricultura'),
('Sherwin-Williams', 'Pinturas y revestimientos'),
('Würth', 'Tornillería y productos industriales'),
('Makita', 'Herramientas eléctricas japonesas');

-- SUCURSALES

-- Insertar sucursales
INSERT INTO sucursal (nombre, direccion, comuna_id, telefono_sucursal) VALUES
('Ferramas Centro', 'Av. Libertador Bernardo O''Higgins 123', 1, '228888888'),
('Ferramas Providencia', 'Av. Providencia 2154', 2, '229999999'),
('Ferramas Valparaíso', 'Av. Argentina 456', 6, '322222222'),
('Ferramas Rancagua', 'Av. Millán 1234', 7, '722333444'),
('Ferramas Las Condes', 'Av. Apoquindo 7890', 3, '225556666'),
('Ferramas Viña del Mar', 'Av. San Martín 1500', 4, '322111222'),
('Ferramas Arica', 'Av. Santa María 800', 9, '582345678'),
('Ferramas Iquique', 'Av. Héroes de la Concepción 100', 10, '572345678');

-- USUARIOS

INSERT INTO usuario (nombre, apellidop, apellidom, email, telefono, direccion, fecha_nacimiento, fecha_registro, activo, rol_id, sucursal_id, ultimo_login, rut, password,comuna_id) VALUES
('Juan', 'Pérez', 'González', 'juan.perez@ferramas.cl', '987654321', 'Los Alerces 123', '1985-05-15', '2023-01-10', TRUE, 1, 1, '2025-04-25', '12345678-9', '$2a$10$OyoWlKfchC/vzbGCMXfN9ObGilU09/jKZ7o6gjSUFKEgHu7fKnksW',1),
('María', 'López', 'Soto', 'maria.lopez@gmail.com', '912345678', 'Las Acacias 456', '1990-10-22', '2023-02-15', TRUE, 2, NULL, '2025-04-26', '98765432-1', '$2a$10$OyoWlKfchC/vzbGCMXfN9ObGilU09/jKZ7o6gjSUFKEgHu7fKnksW',2),
('Carlos', 'Rodríguez', 'Muñoz', 'carlos.rodriguez@ferramas.cl', '923456789', 'Los Olmos 789', '1988-07-30', '2023-03-05', TRUE, 3, 2, '2025-04-27', '11222333-4', '$2a$10$OyoWlKfchC/vzbGCMXfN9ObGilU09/jKZ7o6gjSUFKEgHu7fKnksW',1),
('Ana', 'Martínez', 'Silva', 'ana.martinez@ferramas.cl', '934567890', 'Los Pinos 101', '1992-12-08', '2023-04-20', TRUE, 4, 3, '2025-04-27', '22333444-5', '$2a$10$OyoWlKfchC/vzbGCMXfN9ObGilU09/jKZ7o6gjSUFKEgHu7fKnksW',3),
('Pedro', 'Gómez', 'Rojas', 'pedro.gomez@ferramas.cl', '945678901', 'Los Cipreses 202', '1980-03-25', '2023-05-12', TRUE, 5, 1, '2025-04-26', '33444555-6', '$2a$10$OyoWlKfchC/vzbGCMXfN9ObGilU09/jKZ7o6gjSUFKEgHu7fKnksW',3),
('Laura', 'Fernández', 'Torres', 'laura.fernandez@gmail.com', '956789012', 'Las Palmeras 303', '1995-08-18', '2023-06-30', TRUE, 2, NULL, '2025-04-25', '44555666-7', '$2a$10$OyoWlKfchC/vzbGCMXfN9ObGilU09/jKZ7o6gjSUFKEgHu7fKnksW',4),
('Roberto', 'Díaz', 'Fuentes', 'roberto.diaz@ferramas.cl', '967890123', 'Las Encinas 404', '1982-09-10', '2023-07-05', TRUE, 3, 4, '2025-04-28', '55666777-8', '$2a$10$OyoWlKfchC/vzbGCMXfN9ObGilU09/jKZ7o6gjSUFKEgHu7fKnksW',1),
('Carmen', 'Sánchez', 'Vera', 'carmen.sanchez@ferramas.cl', '978901234', 'Los Aromos 505', '1993-11-20', '2023-08-15', TRUE, 4, 5, '2025-04-29', '66777888-9', '$2a$10$OyoWlKfchC/vzbGCMXfN9ObGilU09/jKZ7o6gjSUFKEgHu7fKnksW',2);



-- Tabla producto
INSERT INTO producto (nombre, descripcion, codigo, activo, precio_actual, costo, fecha_creacion, fecha_actualizacion, garantia_meses, categoria_id, marca_id) VALUES 
('Taladro Percutor 800W', 'Taladro percutor profesional 800W con maletín', 'BOSCH-TP800', TRUE, 69990, 45000, '2024-10-01', '2025-04-15', 24, 2, 1),
('Set Destornilladores 10 piezas', 'Set destornilladores planos y phillips', 'STAN-SD10', TRUE, 19990, 10000, '2024-09-15', '2025-04-10', 12, 1, 2),
('Martillo Carpintero 20oz', 'Martillo de acero con mango ergonómico', 'TRAM-MC20', TRUE, 8990, 4500, '2024-08-10', '2025-03-20', 6, 1, 3),
('Impermeabilizante 5kg', 'Impermeabilizante para muros y techos', 'SIKA-IM5', TRUE, 24990, 15000, '2024-07-05', '2025-02-15', 0, 3, 4),
('Semillas Césped 1kg', 'Semillas de césped para jardín', 'ANAS-SC1', TRUE, 7990, 4000, '2024-06-20', '2025-01-10', 0, 7, 5),
('Pintura Látex 20L Blanco', 'Pintura látex lavable interior/exterior', 'SHERW-PL20B', TRUE, 49990, 32000, '2024-05-15', '2025-04-01', 0, 5, 6),
('Set Tornillos Madera 500u', 'Set de tornillos para madera variados', 'WRTH-TM500', TRUE, 15990, 8000, '2024-04-10', '2025-03-15', 0, 4, 7),
('Sierra Circular 1500W', 'Sierra circular profesional con disco', 'MAK-SC1500', TRUE, 89990, 60000, '2024-03-05', '2025-02-20', 12, 2, 8);

-- INVENTARIO

-- Tabla inventario
INSERT INTO inventario (stock_actual, stock_minimo, ubicacion, producto_id, sucursal_id) VALUES 
(25, 5, 'Bodega A-101', 1, 1),
(15, 3, 'Bodega A-102', 2, 1),
(30, 10, 'Bodega B-201', 3, 1),
(20, 5, 'Bodega A-201', 1, 2),
(10, 3, 'Bodega A-202', 2, 2),
(40, 15, 'Bodega C-101', 4, 3),
(35, 10, 'Bodega C-102', 5, 3),
(12, 4, 'Bodega D-101', 6, 4),
(18, 5, 'Bodega A-301', 7, 5),
(22, 8, 'Bodega A-302', 8, 6);

-- MOVIMIENTOS DE INVENTARIO

-- Tabla movimiento_inventario
INSERT INTO movimiento_inventario (cantidad, fecha_movimiento, motivo, pedido_id, producto_id, sucursal_id, tipo_movimiento_id, usuario_id) VALUES 
(10, '2025-01-10 09:30:00', 'Ingreso inicial', NULL, 1, 1, 1, 1),
(5, '2025-01-12 14:45:00', 'Ingreso por compra', NULL, 2, 1, 1, 1),
(2, '2025-01-15 11:20:00', 'Salida por venta', NULL, 1, 1, 2, 3),
(1, '2025-01-18 16:30:00', 'Salida por venta', NULL, 2, 1, 2, 3),
(3, '2025-01-20 10:15:00', 'Ajuste por inventario', NULL, 3, 1, 3, 4),
(5, '2025-02-05 09:45:00', 'Traslado entre sucursales', NULL, 1, 1, 5, 4),
(2, '2025-02-10 13:20:00', 'Salida por daño', NULL, 4, 3, 4, 4),
(15, '2025-02-15 11:10:00', 'Ingreso por compra', NULL, 5, 3, 1, 1),
(3, '2025-03-01 14:30:00', 'Salida por venta', NULL, 6, 4, 2, 7),
(4, '2025-03-05 10:45:00', 'Ingreso por devolución', NULL, 7, 5, 1, 1);

-- PEDIDOS Y DETALLES

-- Tabla pedido
INSERT INTO pedido (fecha_pedido, direccion_entrega, total, subtotal, iva, notas, descuento, codigo_pedido, cliente_id, estado_id, sucursal_id, tipo_entrega_id, vendedor_id) VALUES 
('2025-01-15 10:30:00', 'Los Alerces 123, Santiago', 78980, 66370, 12610, 'Entregar en horario de oficina', 10000, 'FRR-001', 2, 6, 1, 1, 3),
('2025-01-18 15:45:00', 'Las Acacias 456, Providencia', 89990, 75622, 14368, NULL, 0, 'FRR-002', 2, 6, 1, 1, 3),
('2025-03-01 14:00:00', NULL, 23980, 20151, 3829, 'Cliente retira en tienda', 5000, 'FRR-003', 6, 6, 2, 2, 7),
('2025-04-10 11:30:00', 'Los Pinos 101, Las Condes', 24990, 21000, 3990, NULL, 0, 'FRR-004', 2, 5, 3, 1, 3),
('2025-04-15 09:15:00', 'Las Palmeras 303, Santiago', 59980, 50403, 9577, 'Incluir manual de uso', 8000, 'FRR-005', 6, 3, 1, 1, 3),
('2025-04-20 16:45:00', NULL, 19990, 16798, 3192, 'Cliente retira en tienda', 0, 'FRR-006', 2, 2, 4, 2, 7),
('2025-04-25 13:20:00', 'Los Cipreses 202, Viña del Mar', 89990, 75622, 14368, 'Entregar en horario vespertino', 0, 'FRR-007', 6, 1, 6, 1, 3),
('2025-04-27 10:10:00', 'Av. Libertad 567, Viña del Mar', 31980, 26874, 5106, NULL, 3000, 'FRR-008', 2, 1, 6, 1, 3);

-- Tabla detalle_pedido
INSERT INTO detalle_pedido (cantidad, descuento_unitario, precio_unitario, total_linea, pedido_id, producto_id) VALUES 
(1, 10000, 69990, 59990, 1, 1),
(2, 0, 8990, 17980, 1, 3),
(1, 0, 89990, 89990, 2, 8),
(3, 5000, 8990, 21970, 3, 3),
(1, 0, 24990, 24990, 4, 4),
(1, 8000, 49990, 41990, 5, 6),
(2, 0, 8990, 17980, 5, 3),
(1, 0, 19990, 19990, 6, 2),
(1, 0, 89990, 89990, 7, 8),
(3, 1000, 7990, 20970, 8, 5),
(1, 0, 8990, 8990, 8, 3),
(2, 0, 15990, 31980, 6, 7);

-- DESPACHOS

-- Tabla despachos
INSERT INTO despachos (fecha_despacho, fecha_entrega_estimada, fecha_entrega_real, numero_guia, transportista, estado_id, pedido_id) VALUES 
('2025-01-16 08:45:00', '2025-01-18', '2025-01-18', 'GD-00001', 'Transportes Rápidos', 4, 1),
('2025-01-19 09:30:00', '2025-01-21', '2025-01-21', 'GD-00002', 'Transportes Rápidos', 4, 2),
('2025-03-02 10:15:00', '2025-03-02', '2025-03-02', NULL, 'Retiro en tienda', 4, 3),
('2025-04-11 11:00:00', '2025-04-13', NULL, 'GD-00003', 'Envíos Express', 3, 4),
('2025-04-16 09:45:00', '2025-04-18', NULL, 'GD-00004', 'Transportes Rápidos', 2, 5),
('2025-04-21 14:30:00', '2025-04-21', NULL, NULL, 'Retiro en tienda', 1, 6);

-- PAGOS

-- Tabla pago
INSERT INTO pago (monto, fecha_pago, codigo_transaccion, datos_transaccion, estado_id, metodo_pago_id, pedido_id) VALUES 
(78980, '2025-01-15 10:45:00', 'TR-00001', 'Tarjeta terminada en 4567', 3, 1, 1),
(89990, '2025-01-18 15:55:00', 'TR-00002', 'Tarjeta terminada en 1234', 3, 1, 2),
(23980, '2025-03-01 14:15:00', 'TR-00003', 'Efectivo en caja', 3, 4, 3),
(24990, '2025-04-10 11:40:00', 'TR-00004', 'Transferencia N° 98765', 3, 3, 4),
(30000, '2025-04-15 09:25:00', 'TR-00005', 'Abono tarjeta terminada en 7890', 3, 1, 5),
(29980, '2025-04-15 13:10:00', 'TR-00006', 'Saldo tarjeta terminada en 7890', 2, 1, 5),
(19990, '2025-04-20 16:55:00', 'TR-00007', 'Tarjeta débito terminada en 5678', 3, 2, 6),
(40000, '2025-04-25 13:30:00', 'TR-00008', 'Abono transferencia N° 12345', 2, 3, 7);

-- TABLAS DE HISTORIAL

-- Tabla historico_precios
INSERT INTO historial_precios (fecha_cambio, precio_anterior, precio_nuevo, motivo, producto_id, usuario_id) VALUES 
('2024-12-01', 59990, 69990, 'Actualización por costos', 1, 1),
('2024-12-15', 79990, 89990, 'Aumento por importación', 8, 1),
('2025-01-10', 9990, 8990, 'Promoción temporada', 3, 1),
('2025-02-05', 21990, 24990, 'Aumento por costos', 4, 1),
('2025-03-01', 8990, 7990, 'Oferta liquidación', 5, 1);

-- Tabla historico_estado_pedido
INSERT INTO historial_estado_pedido (fecha_cambio, comentario, estado_anterior_id, estado_nuevo_id, pedido_id, usuario_id) VALUES 
('2025-01-15 10:30:00', 'Creación del pedido', NULL, 1, 1, 3),
('2025-01-15 10:45:00', 'Pago recibido', 1, 2, 1, 3),
('2025-01-15 11:30:00', 'En preparación', 2, 3, 1, 4),
('2025-01-15 14:00:00', 'Listo para despacho', 3, 4, 1, 4),
('2025-01-16 08:45:00', 'En despacho', 4, 5, 1, 5),
('2025-01-18 16:30:00', 'Entregado al cliente', 5, 6, 1, 5),
('2025-01-18 15:45:00', 'Creación del pedido', NULL, 1, 2, 3),
('2025-01-18 15:55:00', 'Pago recibido', 1, 2, 2, 3),
('2025-01-18 16:30:00', 'En preparación', 2, 3, 2, 4),
('2025-01-19 09:00:00', 'Listo para despacho', 3, 4, 2, 4),
('2025-01-19 09:30:00', 'En despacho', 4, 5, 2, 5),
('2025-01-21 15:45:00', 'Entregado al cliente', 5, 6, 2, 5),
('2025-03-01 14:00:00', 'Creación del pedido', NULL, 1, 3, 7),
('2025-03-01 14:15:00', 'Pago recibido', 1, 2, 3, 7),
('2025-03-01 14:45:00', 'En preparación', 2, 3, 3, 4);

-- VOLVER A ACTIVAR LAS RESTRICCIONES DE CLAVE FORÁNEA

SET FOREIGN_KEY_CHECKS = 1;





