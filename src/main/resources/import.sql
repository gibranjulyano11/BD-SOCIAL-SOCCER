INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('AMAZONAS', 'MAXIMA', 'FELIX', '1986-10-01');
INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('YupiS', 'MAXIMA', 'DARWIN', '1976-10-01');
INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('Chocolate', 'MAXIMA', 'JOEL', '1986-10-01');
INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('CanelaS', 'MAXIMA', 'JOLO', '2000-10-01');

INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('Amazon', 'MAXIMA', 'FELIX', '1986-10-01');
INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('YUPIX', 'MAXIMA', 'DARWIN', '1976-10-01');
INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('CHOCOLO', 'MAXIMA', 'JOEL', '1986-10-01');
INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('CanelaSC', 'MAXIMA', 'JOLO', '2000-10-01');

INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('AmazonasFC', 'MAXIMA', 'FELIX', '1986-10-01');
INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('YupiFC', 'MAXIMA', 'DARWIN', '1976-10-01');
INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('ChocolateFC', 'MAXIMA', 'JOEL', '1986-10-01');
INSERT INTO EQUIPOS (NOMBRE, CATEGORIA, DIRIGENTE, create_at) VALUES ('CanelaFC', 'MAXIMA', 'JOLO', '2000-10-01');



INSERT INTO `usuarios` (equipo_id, username, password, enabled, nombre, apellido, email) VALUES (1, 'joel','$2a$10$lRC8NVT1jadph5RYR1Rrse1ctRXCluBffEZx7H0f.6HGgLNpPb.fq',1, 'Joel', 'Drouet','mosquera593@gmail.com');
INSERT INTO `usuarios` (equipo_id, username, password, enabled, nombre, apellido, email) VALUES (1, 'admin','$2a$10$kxbNA/KSvi6DT9MzkgCuhuRUUuiaMBtBpYeWm0Spynp6z1IYVlFle',1, 'Darwin', 'Mosquera','drouet593@gmail.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);

INSERT INTO multas (nombre, precio, create_at) VALUES('Targeta roja', 2, NOW());
INSERT INTO multas (nombre, precio, create_at) VALUES('Targeta amarilla', 1, NOW());
INSERT INTO multas (nombre, precio, create_at) VALUES('Arbitraje', 12, NOW());

INSERT INTO facturas (descripcion, observacion, equipo_id, create_at) VALUES('Factura partido 2', null, 1, NOW());
INSERT INTO facturas_items(cantidad, factura_id, multa_id) VALUES(1, 1, 1);
INSERT INTO facturas_items(cantidad, factura_id, multa_id) VALUES(2, 1, 3);
INSERT INTO facturas_items(cantidad, factura_id, multa_id) VALUES(1, 1, 2);
INSERT INTO facturas_items(cantidad, factura_id, multa_id) VALUES(1, 1, 1);

INSERT INTO facturas (descripcion, observacion, equipo_id, create_at) VALUES('Factura partido 1', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items(cantidad, factura_id, multa_id) VALUES(1, 2, 3);

