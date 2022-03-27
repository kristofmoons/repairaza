INSERT INTO "PUBLIC"."ITEM" VALUES
                                (1, TRUE, 'omega seamaster', 1000.0, 'watch'),
                                (2, TRUE, 'cadillac de ville', 25000.0, 'car'),
                                (3, TRUE, 'retro chair', 250.0, 'furniture'),
                                (4, FALSE, 'gipson stratocaster', 2000.0, 'instrument');
INSERT INTO "PUBLIC"."WORKER" VALUES
                                  (1, 'Ik vond als kind dingen restaureren leuk', 'RESTAUREUR', 'Jos De Wolf'),
                                  (2, 'Ik vond het altijd leuk om mensen af te zetten met verkeerde prijzen ', 'TAXATEUR', 'Jef Het Schaap'),
                                  (3, 'ik heb iets geleerd en bladi bla bla', 'RESTAUREUR', 'Tim De Kat');
INSERT INTO ITEM_WORKERS (ITEMS_ID, WORKERS_ID) VALUES
                                                       (1, 1),
                                                       (2, 3),
                                                       (3, 1),
                                                       (4, 1),
                                                       (1, 2),
                                                       (2, 2),
                                                       (3, 2),
                                                       (4, 2);

