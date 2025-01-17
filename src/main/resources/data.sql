-- Insertar  datos en la tabla 'coordenadas'
INSERT IGNORE INTO coordenada (latitud, longitud) VALUES (40.7128, -74.0060);
INSERT IGNORE INTO coordenada (latitud, longitud) VALUES (34.0522, -118.2437);
INSERT IGNORE INTO coordenada (latitud, longitud) VALUES (41.8781, -87.6298);
INSERT IGNORE INTO coordenada (latitud, longitud) VALUES (29.7604, -95.3698);
INSERT IGNORE INTO coordenada (latitud, longitud) VALUES (39.7392, -104.9903);


-- Insertar datos en la tabla 'vendedores'
INSERT IGNORE INTO vendedor (activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 1, NULL, CURDATE(), 'Avenida Siempre Viva 742', 'Gaia');
INSERT IGNORE INTO vendedor (activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 2, NULL, CURDATE(), 'Calle Principal 123', 'La Buena Pizza');
INSERT IGNORE INTO vendedor (activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 3, NULL, CURDATE(), 'Boulevard Central 456', 'Kiosko Pancho');
INSERT IGNORE INTO vendedor (activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 4, NULL, CURDATE(), 'Avenida del Sol 789', 'Cafe Amanecer');
INSERT IGNORE INTO vendedor (activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 5, NULL, CURDATE(), 'Plaza Mayor 101', 'Sabor y Tradicion');
