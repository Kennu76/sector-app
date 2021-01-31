create table MAIN_SECTOR (
    main_sector_id int not null,
    NAME varchar(100) not null,
    PRIMARY KEY (main_sector_id)
);

create table LEVEL_2_SECTOR (
    level_2_sector_id int not null,
    NAME varchar(100) not null,
    main_sector_id int,
    PRIMARY KEY (level_2_sector_id),
    CONSTRAINT FK_MainSector FOREIGN KEY (main_sector_id)
    REFERENCES MAIN_SECTOR(main_sector_id)
);

CREATE TABLE LEVEL_3_SECTOR (
    level_3_sector_id int not null,
    NAME varchar(100) not null,
    level_2_sector_id int,
    PRIMARY KEY (level_3_sector_id),
    CONSTRAINT FK_Level2Sector FOREIGN KEY (level_2_sector_id)
    REFERENCES LEVEL_2_SECTOR(level_2_sector_id)
);

CREATE TABLE LEVEL_4_SECTOR (
    level_4_sector_id int not null,
    NAME varchar(100) not null,
    level_3_sector_id int,
    PRIMARY KEY (level_4_sector_id),
    CONSTRAINT FK_Level3Sector FOREIGN KEY (level_3_sector_id)
    REFERENCES LEVEL_3_SECTOR(level_3_sector_id)
);

INSERT INTO 
	MAIN_SECTOR(main_sector_id, name)
VALUES
	(1,'Manufacturing'),
    (2,'Other'),
	(3,'Service');

INSERT INTO 
	LEVEL_2_SECTOR(level_2_sector_id, name, main_sector_id)
VALUES
	(1,'Construction materials', 1),
    (2,'Electronics and Optics', 1),
    (3,'Food and Beverage', 1),
    (4,'Furniture', 1),
    (5,'Machinery', 1),
    (6,'Metalworking', 1),
    (7,'Plastic and rubber', 1),
    (8,'Printing', 1),
    (9,'Textile and Clothing', 1),
    (10,'Wood', 1),

    (11,'Creative industries', 2),
    (12,'Energy technology', 2),
    (13,'Environment', 2),
    
    (14,'Business services', 3),
    (15,'Engineering', 3),
    (16,'Information Technology and Telecommunications', 3),
    (17,'Tourism', 3),
    (18,'Translation services', 3),
    (19,'Transport and Logistics', 3);

INSERT INTO 
	LEVEL_3_SECTOR(level_3_sector_id, name, level_2_sector_id)
VALUES
	
    (1,'Bakery & confectionery products', 3),
    (2,'Beverages', 3),
    (3,'Fish & fish products ', 3),
    (4,'Meat & meat products', 3),
    (5,'Milk & dairy products ', 3),
    (6,'Other', 3),
    (7,'Sweets & snack food', 3),

    (8,'Bathroom/sauna ', 4),
    (9,'Bedroom', 4),
    (10,'Childrenâ€™s room ', 4),
    (11,'Kitchen ', 4),
    (12,'Living room ', 4),
    (13,'Office', 4),
    (14,'Other (Furniture)', 4),
    (15,'Outdoor ', 4),
    (16,'Project furniture', 4),

    (17,'Machinery components', 5),
    (18,'Machinery equipment/tools', 5),
    (19,'Manufacture of machinery ', 5),
    (20,'Maritime', 5),
    (21,'Metal structures', 5),
    (22,'Other', 5),
    (23,'Repair and maintenance service', 5),

    (24,'Construction of metal structures', 6),
    (25,'Houses and buildings', 6),
    (26,'Metal products', 6),
    (27,'Metal works', 6),
    
    (28,'Packaging', 7),
    (29,'Plastic goods', 7),
    (30,'Plastic processing technology', 7),
    (31,'Plastic profiles', 7),

    (32,'Advertising', 8),
    (33,'Book/Periodicals printing', 8),
    (34,'Labelling and packaging printing', 8),

    (35,'Clothing', 9),
    (36,'Textile', 9),
    
    (37,'Other (Wood)', 10),
    (38,'Wooden building materials', 10),
    (39,'Wooden houses', 10),

    (40,'Data processing, Web portals, E-marketing', 16),
    (41,'Programming, Consultancy', 16),
    (42,'Software, Hardware', 16),
    (43,'Telecommunications', 16),

    (44,'Air', 19),
    (45,'Rail', 19),
    (46,'Road', 19),
    (47,'Water', 19);


INSERT INTO 
	LEVEL_4_SECTOR(level_4_sector_id, name, level_3_sector_id)
VALUES	
    (1,'Aluminium and steel workboats ', 20),
    (2,'Boat/Yacht building', 20),
    (3,'Ship repair and conversion', 20),
    
    (4,'CNC-machining', 26),
    (5,'Forgings, Fasteners ', 26),
    (6,'Gas, Plasma, Laser cutting', 26),
    (7,'MIG, TIG, Aluminum welding', 26),

    (8,'Blowing', 29),
    (9,'Moulding', 29),
    (11,'Plastics welding and processing', 29);
