INSERT INTO USUARIO(USUARIO, PASSWORD, EMAIL, FECHA_NACIMIENTO, NOMBRE, APELLIDO, EDAD, ROL) VALUES
    ('test','$2a$10$bhoNyi0sxaGQu.50zOiUL.SKpSPGHytwGruHhYYP2hSDZpkYHEIri', 'test@test.com', '2012-09-17', 'test','test', 21, 1),
    ('admin','$2a$10$bhoNyi0sxaGQu.50zOiUL.SKpSPGHytwGruHhYYP2hSDZpkYHEIri', 'admin@admin.com', '2012-09-17', 'admin','admin', 99, 0),
    ('user1', '$2a$10$bhoNyi0sxaGQu.50zOiUL.SKpSPGHytwGruHhYYP2hSDZpkYHEIri', 'user1@test.com', '1990-01-15', 'User', 'One', 34, 1),
    ('user2', '$2a$10$bhoNyi0sxaGQu.50zOiUL.SKpSPGHytwGruHhYYP2hSDZpkYHEIri', 'user2@test.com', '1985-05-21', 'User', 'Two', 39, 1),
    ('user3', '$2a$10$bhoNyi0sxaGQu.50zOiUL.SKpSPGHytwGruHhYYP2hSDZpkYHEIri', 'user3@test.com', '2000-12-01', 'User', 'Three', 23, 1),
    ('admin2', '$2a$10$bhoNyi0sxaGQu.50zOiUL.SKpSPGHytwGruHhYYP2hSDZpkYHEIri', 'admin2@admin.com', '1975-07-30', 'Admin', 'Second', 49, 0);


INSERT INTO PRODUCTO(NOMBRE, DESCRIPCION, IMAGEN, PRECIO, STATUS) VALUES
    ('Adidas JumpCrazy', 'Modo rana all day', 'https://m.media-amazon.com/images/I/71E75yRwCDL._AC_UY575_.jpg', 99.99, 1),
    ( 'Vans Classics', 'Un clasicon', 'https://http2.mlstatic.com/D_775466-MLA74462197455_022024-C.jpg', 99.99, 1),
    ( 'DC Big Skater', 'Para ese fan que le encanta quemar zapas caras con la lija del board', 'https://http2.mlstatic.com/D_Q_NP_2X_978135-MLA77089591492_062024-E.webp', 299.99, 1),
    ( 'Nike Air Max', 'Comodidad y estilo para todos', 'https://http2.mlstatic.com/D_Q_NP_2X_632832-MLA73309925273_122023-E.webp', 149.99, 1),
    ( 'Converse All Star', 'El clásico de siempre, nunca pasa de moda', 'https://http2.mlstatic.com/D_Q_NP_2X_948907-MLA71811052528_092023-E.webp', 79.99, 1),
    ( 'Reebok Classic', 'El modelo retro que sigue vigente', 'https://http2.mlstatic.com/D_Q_NP_2X_791711-MLA79915847808_102024-E.webp', 89.99, 1),
    ( 'Puma Suede', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( 'Adidas Stan Smith','Clásico atemporal, conocido por su diseño minimalista y su comodidad. Perfectas para un estilo casual y versátiles para cualquier ocasión.','https://assets.adidas.com/images/w_600,f_auto,q_auto/f6bfb2c064a64c498e57af1700593332_9366/Stan_Smith_Lux_Shoes_White_HQ6785_HM1.jpg',99.99,1),
    ( 'Adidas Ultraboost', 'Excelente amortiguación y soporte, ideales para correr y entrenar.','https://cdn.sanity.io/images/c1chvb1i/production/cd0f052dc8b293b2626d748b4bf72c34f36c7948-1100x735.jpg/ULTRABOOST_WMNS_WHT-BB6308-1.jpg',109.99, 1),
    ( 'Adidas Samba', 'Icono de la marca, con su distintivo diseño de tres líneas negras y suela de cuero.','https://th.bing.com/th/id/OIP.4ihPcQTb2TRzG-PI2cwQ5QAAAA?rs=1&pid=ImgDetMain',120.00, 1),
    ( 'Nike Air Force 1', 'Clásico icónico conocido por su diseño retro y su comodidad.', 'https://www.favsole.com/images/2018/08/Nike-Air-Force-1-Low-Utility-White-Black-Tour-Yellow-AJ7747-100-On-Sale-3.jpg', 100.00, 1),
    ( 'Nike Air Max 90', 'Un clásico deportivo con su distintivo "Air" en la suela y su diseño retro.', 'https://th.bing.com/th/id/OIP.znW2foQq4z6MRjiFG4UG6wHaFS?rs=1&pid=ImgDetMain', 109.99, 1),
    ( 'Nike Dunk', 'Con un diseño moderno y versátil, estas zapatillas son perfectas para el uso diario y el deporte.','https://sneakerbardetroit.com/wp-content/uploads/2023/07/Nike-Dunk-Low-Anthracite-Pure-Platinum-Cool-Grey-FV0384-001-4.jpg', 99.99, 1),
    ( 'Puma Skyrocket Lite', 'Diseñadas para el rendimiento, estas zapatillas ofrecen gran durabilidad y tracción.','https://celadasa.vtexassets.com/arquivos/ids/294737-800-auto?v=638519731871870000&width=800&height=auto&aspect=true', 79.99, 1),
    ( 'Puma Scend Pro', 'Perfectas para correr largas distancias, estas zapatillas cuentan con una suela de goma Protread y un exterior de malla transpirable', 'https://th.bing.com/th/id/OIP.fuVOZYVbAj-4CySQqnK9nAHaKs?rs=1&pid=ImgDetMain', 75.00, 1),
    ( 'Puma Deviate Nitro', 'Con un diseño avanzado y tecnología de amortiguación superior', 'https://th.bing.com/th/id/OIP._zCZ7PDrAsypX8HO5T_gOQHaHa?rs=1&pid=ImgDetMain', 94.99, 1),
    ( '999', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '112', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '113', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '114', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '115', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '116', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '117', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '118', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '119', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '221', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '223', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '224', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '225', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '226', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '227', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '228', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '229', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '331', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1),
    ( '332', 'Elegancia y rendimiento en uno', 'https://http2.mlstatic.com/D_NQ_NP_2X_602116-MLA70284525307_072023-F.webp', 129.99, 1);

INSERT INTO STOCK_PRODUCTO(PRODUCTO_ID, TALLE, CANTIDAD) VALUES
    (1, 40, 3), ---ADIDAS JUMPCRAZY
    (1, 42, 10), ---ADIDAS JUMPCRAZY
    (1, 37, 1), ---ADIDAS JUMPCRAZY
    (2, 30, 2), ---VANS CLASSICS
    (3, 46, 5), ---DC BIG SKATER
    (4, 41, 8), -- NIKE AIR MAX
    (4, 43, 5), -- NIKE AIR MAX
    (5, 38, 12), -- CONVERSE ALL STAR
    (5, 40, 6), -- CONVERSE ALL STAR
    (6, 39, 15), -- REEBOK CLASSIC
    (6, 44, 7), -- REEBOK CLASSIC
    (7, 42, 9), -- PUMA SUEDE
    (7, 44, 4), -- PUMA SUEDE
    (8,41,5), -- Stan smith
    (8,43,2), -- Stan smith
    (9,39,6), -- UltraBoost
    (9,42,3), -- UltraBoost
    (10,44,10), -- Samba
    (11,40,10), -- Air Force 1
    (12,38,9), -- Air Max 90
    (13, 41,4), -- Nike Dunk
    (13,42,5), -- Nike Dunk
    (14,40,5),
    (15,41,3),
    (16, 39,6);

INSERT INTO FACTURA(FECHA_COMPRA, USUARIO_ID) VALUES
    ('2024-11-12', 3),
    ('2024-11-13', 3),
    ('2024-11-14', 3),
    ('2024-11-15', 3),
    ('2024-11-16', 3),
    ('2024-11-17', 3),
    ('2024-11-18', 3),
    ('2024-11-19', 3),
    ('2024-11-20', 3),
    ('2024-11-21', 3),
    ('2024-11-22', 3),
    ('2024-11-23', 3),
    ('2024-11-24', 3),
    ('2024-11-25', 3),
    ('2024-11-26', 3),
    ('2024-11-27', 3),
    ('2024-11-28', 3),
    ('2024-11-30', 3),
    ('2024-12-31', 3),
    ('2024-12-20', 3);


INSERT INTO GRUPO_CATEGORIA(ID, NOMBRE) VALUES (1, 'Marca');
INSERT INTO GRUPO_CATEGORIA(ID, NOMBRE) VALUES (2, 'Segmento');
INSERT INTO GRUPO_CATEGORIA(ID, NOMBRE) VALUES (3, 'Deporte');
INSERT INTO GRUPO_CATEGORIA(ID, NOMBRE) VALUES (4, 'Estilo');

INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (1, 1, 'Adidas');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (1, 2, 'Vans');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (1, 3, 'DC');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (2, 4, 'Hombre');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (2, 5, 'Mujer');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (2, 6, 'Niño');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (2, 7, 'Niña');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (3, 8, 'Running');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (3, 9, 'Skateboarding');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (4, 10, 'Casual');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (4, 11, 'Elegante');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (1, 12, 'Converse');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (1, 13, 'Reebok');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (1, 14, 'Puma');
INSERT INTO CATEGORIA(GRUPO_ID, ID, NOMBRE) VALUES (1, 15, 'Nike');


INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (1, 1);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (1, 4);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (2, 2);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (2, 4);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (3, 3);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (3, 5);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (4, 15); -- NIKE AIR MAX -> Nike (Marca)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (4, 8); -- NIKE AIR MAX -> Running (Deporte)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (5, 12); -- CONVERSE ALL STAR -> Converse (Marca)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (5, 10); -- CONVERSE ALL STAR -> Casual (Estilo)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (6, 13); -- REEBOK CLASSIC -> DC (Marca)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (6, 9); -- REEBOK CLASSIC -> Skateboarding (Deporte)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (7, 14); -- PUMA SUEDE -> DC (Marca)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (7, 11); -- PUMA SUEDE -> Elegante (Estilo)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (8, 10); -- Stan -> (Estilo)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (8, 1); -- Stan -> (Marca)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (8, 4); -- Stan -> (Segmento)
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (9, 5);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (9, 1);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (9, 8);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (10, 1);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (10, 4);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (10, 10);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (11, 15);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (11, 4);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (11, 10);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (12, 15);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (12, 10);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (13, 15);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (13, 10);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (14, 14);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (14, 8);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (15, 14);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (15, 8);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (16, 14);
INSERT INTO PRODUCTO_CATEGORIA(PRODUCTO_ID, CATEGORIA_ID) VALUES (16, 8);

