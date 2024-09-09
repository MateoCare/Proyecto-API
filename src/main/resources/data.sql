INSERT INTO USUARIO(USUARIO, PASSWORD, EMAIL, FECHA_NACIMIENTO, NOMBRE, APELLIDO, EDAD) VALUES
    ('test','abc123', 'test@test.com', '2012-09-17', 'test','test', 21),
    ('admin','admin', 'admin@admin.com', '2012-09-17', 'admin','', 99);

INSERT INTO PRODUCTO(NOMBRE, DESCRIPCION, IMAGEN, PRECIO) VALUES
    ('Adidas JumpCrazy', 'Modo rana all day', 'jumpcrazy.jpg', 99.99),
    ('Vans Classics', 'Un clasicon', 'vansclassic.jpg', 99.99),
    ('DC Big Skater', 'Para ese fan que le encanta quemar zapas caras con la lija del board', 'dcbigskater.jpg', 299.99);

INSERT INTO STOCK_PRODUCTO(PRODUCTO_ID, TALLE, CANTIDAD) VALUES
    (1, 40, 3), ---ADIDAS JUMPCRAZY
    (1, 42, 10), ---ADIDAS JUMPCRAZY
    (1, 37, 1), ---ADIDAS JUMPCRAZY
    (2, 30, 2), ---VANS CLASSICS
    (3, 46, 5); ---DC BIG SKATER
