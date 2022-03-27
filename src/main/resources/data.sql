INSERT INTO "PUBLIC"."ITEM" VALUES
                                (1, TRUE,'een horloge van omega gemaakt voor te gaan duiken', 'omega seamaster', 1000.0, 'watch'),
                                (2, TRUE,'een klassier van wagens gemaakt in 1962', 'cadillac de ville', 25000.0, 'car'),
                                (3, TRUE,'een zeer mooie stoel met een retro look', 'retro chair', 250.0, 'furniture'),
                                (4, FALSE,'een elecktrische gitaar van gibson', 'gipson stratocaster', 2000.0, 'instrument'),
                                (5, FALSE, 'een horloge met een sepciale look van tissot', 'tissot couturier', 600.0, 'watch');

INSERT INTO "PUBLIC"."WORKER" VALUES
                                  (1, 'Ik vond als kind dingen restaureren leuk', 'RESTAUREUR', 'Jos De Wolf'),
                                  (2, 'Ik vond het altijd leuk om mensen af te zetten met verkeerde prijzen ', 'TAXATEUR', 'Jef Het Schaap'),
                                  (3, 'ik heb iets geleerd en bladi bla bla', 'RESTAUREUR', 'Tim De Kat');
INSERT INTO ITEM_WORKERS (ITEMS_ID, WORKERS_ID) VALUES
                                                       (1, 1),
                                                       (2, 3),
                                                       (3, 1),
                                                       (5, 3),
                                                       (4, 1),
                                                       (1, 2),
                                                       (2, 2),
                                                       (3, 2),
                                                       (4, 2),
                                                       (5, 2);

