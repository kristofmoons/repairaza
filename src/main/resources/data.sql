INSERT INTO "PUBLIC"."RESTAUREUR"
VALUES (1, 'Ik vond als kind dingen restaureren leuk', 'Jos De Wolf'),
       (2, 'ik heb iets geleerd en bladi bla bla', 'Tim De Kat');
INSERT INTO "PUBLIC"."TAXATEUR"
VALUES (1, 'Ik vond het altijd leuk om mensen af te zetten met verkeerde prijzen ', 'Jef Het Schaap'),
       (2, 'Ik wou altijd van alles alle prijzen weten', 'louis de visser');

INSERT INTO "PUBLIC"."ITEM"
VALUES (nextval('ITEM_SEQ'), TRUE, 'een horloge van omega gemaakt voor te gaan duiken', 'omega seamaster', 1000.0,
        'watch', 1),
       (nextval('ITEM_SEQ'), TRUE, 'een klassier van wagens gemaakt in 1962', 'cadillac de ville', 25000.0, 'car', 1),
       (nextval('ITEM_SEQ'), TRUE, 'een zeer mooie stoel met een retro look', 'retro chair', 250.0, 'furniture', 1),
       (nextval('ITEM_SEQ'), FALSE, 'een elecktrische gitaar van gibson', 'gipson stratocaster', 2000.0, 'instrument',
        1),
       (nextval('ITEM_SEQ'), FALSE, 'een horloge met een sepciale look van tissot', 'tissot couturier', 600.0, 'watch',
        1);
INSERT INTO ITEM_RESTAUREURS (ITEMS_ID, RESTAUREURS_ID)
VALUES (1, 1),
       (2, 2),
       (3, 1),
       (4, 1),
       (5, 2);

INSERT INTO USER (ID, USERNAME, PASSWORD, ROLE)
VALUES (nextval('USER_SEQ'), 'admin',
        '$2a$10$9MIX8kYPkuB7uE/H5nHF8.KG6.YdjBA/voOnjSZnZDxLXL/2BIerS',
        'ADMIN');
INSERT INTO USER (ID, USERNAME, PASSWORD, ROLE)
VALUES (nextval('USER_SEQ'), 'roland',
        '$2a$10$9TeBFudS7HsgCa4sSvP//O627sMq.KiTFrOr8IzrVlYw5c8aoKzNm',
        'USER');

INSERT INTO "PUBLIC"."LIEFHEBBER"
VALUES
       (nextval('liefhebber_seq'), 'ghosta', 2);



