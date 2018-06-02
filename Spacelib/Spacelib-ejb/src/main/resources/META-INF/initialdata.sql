DELETE FROM TEMPS_TRAJET
DELETE FROM VOYAGE
DELETE FROM TRANSFERT
DELETE FROM REVISION
DELETE FROM QUAI
DELETE FROM NAVETTE
DELETE FROM ADMINISTRATEUR
DELETE FROM CONDUCTEUR
DELETE FROM MECANICIEN
DELETE FROM USAGER
DELETE FROM STATION

INSERT INTO SPACELIBDB.STATION (ID, LOCALISATION, NOMBRE_QUAIS, NOM) VALUES (32, 'sold.d', 20, 'Terre')
INSERT INTO SPACELIBDB.STATION (ID, LOCALISATION, NOMBRE_QUAIS, NOM) VALUES (41, 'pegasi.b', 5, 'Dimidium')
INSERT INTO SPACELIBDB.STATION (ID, LOCALISATION, NOMBRE_QUAIS, NOM) VALUES (54, 'delphini.b', 6, 'Arion')
INSERT INTO SPACELIBDB.STATION (ID, LOCALISATION, NOMBRE_QUAIS, NOM) VALUES (63, 'cancri.c', 2, 'Brahe')
INSERT INTO SPACELIBDB.STATION (ID, LOCALISATION, NOMBRE_QUAIS, NOM) VALUES (84, 'epsilonTauri.b', 9, 'Amateru')
INSERT INTO SPACELIBDB.STATION (ID, LOCALISATION, NOMBRE_QUAIS, NOM) VALUES (98, 'gammaCepheiA.b', 3, 'Tadmor')

INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (43, 2, 41, 32)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (42, 2, 32, 41)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART)  VALUES (56, 6, 54, 32)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (55, 6, 32, 54)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (57, 6, 41, 54)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (58, 6, 54, 41)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (69, 4, 63, 41)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (67, 6, 63, 54)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (66, 6, 54, 63)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (65, 2, 63, 32)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (64, 2, 32, 63)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (68, 4, 41, 63)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (91, 4, 63, 84)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (86, 4, 84, 32)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (90, 6, 84, 41)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (89, 6, 41, 84)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (85, 4, 32, 84)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (88, 8, 84, 54)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (92, 4, 84, 63)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (87, 8, 54, 84)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (104, 6, 98, 54)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (100, 2, 98, 32)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (102, 2, 98, 84)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (106, 4, 98, 41)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (108, 2, 98, 63)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (101, 2, 84, 98)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (105, 4, 41, 98)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (103, 6, 54, 98)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (99, 2, 32, 98)
INSERT INTO SPACELIBDB.TEMPS_TRAJET (ID, TEMPS, ID_STATION_ARRIVEE, ID_STATION_DEPART) VALUES (107, 2, 63, 98)

INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (1, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (7, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (9, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (8, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (5, 15, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (3, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (4, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (10, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (11, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (2, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (6, 10, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (35, 15, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (33, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (34, 10, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (47, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (45, 10, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (44, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (46, 15, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (59, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (60, 10, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (74, 10, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (70, 2, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (72, 5, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (73, 15, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (71, 2, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (94, 10, 3)
INSERT INTO SPACELIBDB.NAVETTE (ID, NBPLACES, NBVOYAGES) VALUES (93, 15, 3)

INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (20, 32, 9)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (28, 32, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (12, 32, 1)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (27, 32, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (16, 32, 5)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (24, 32, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (30, 32, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (25, 32, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (23, 32, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (22, 32, 11)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (19, 32, 8)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (21, 32, 10)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (18, 32, 7)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (17, 32, 6)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (29, 32, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (26, 32, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (13, 32, 2)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (14, 32, 3)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (15, 32, 4)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (31, 32, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (36, 41, 33)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (40, 41, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (39, 41, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (37, 41, 34)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (38, 41, 35)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (53, 54, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (50, 54, 46)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (51, 54, 47)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (49, 54, 45)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (48, 54, 44)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (52, 54, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (61, 63, 59)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (62, 63, 60)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (81, 84, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (80, 84, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (76, 84, 71)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (78, 84, 73)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (82, 84, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (83, 84, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (77, 84, 72)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (75, 84, 70)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (79, 84, 74)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (96, 98, 94)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (97, 98, NULL)
INSERT INTO SPACELIBDB.QUAI (ID, ID_STATION, ID_NAVETTE) VALUES (95, 98, 93)


INSERT INTO USAGER (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (100 , CURRENT_TIMESTAMP, 'usagerl', 'usager', 'UZAN', 'Lucas')
INSERT INTO USAGER (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (101 , CURRENT_TIMESTAMP, 'usagerm', 'usager', 'HENTATI', 'Mahdi')
INSERT INTO USAGER (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (102 , CURRENT_TIMESTAMP, 'usagerp', 'usager', 'ROQUES-GEOFFROY', 'Philippe')
INSERT INTO ADMINISTRATEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (100, CURRENT_TIMESTAMP, 'adminl', 'admin', 'UZAN', 'Lucas')
INSERT INTO ADMINISTRATEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (101 , CURRENT_TIMESTAMP, 'adminm', 'admin', 'HENTATI', 'Mahdi')
INSERT INTO ADMINISTRATEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (102 , CURRENT_TIMESTAMP, 'adminp', 'admin', 'ROQUES-GEOFFROY', 'Philippe')
INSERT INTO CONDUCTEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (100 , CURRENT_TIMESTAMP, 'conducl', 'conduc', 'UZAN', 'Lucas')
INSERT INTO CONDUCTEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (101 , CURRENT_TIMESTAMP, 'conducm', 'conduc', 'HENTATI', 'Mahdi')
INSERT INTO CONDUCTEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (102 , CURRENT_TIMESTAMP, 'conducp', 'conduc', 'ROQUES-GEOFFROY', 'Philippe')
INSERT INTO MECANICIEN (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (100 , CURRENT_TIMESTAMP, 'mecanol', 'mecano', 'UZAN', 'Lucas')
INSERT INTO MECANICIEN (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (101 , CURRENT_TIMESTAMP, 'mecanom', 'mecano', 'HENTATI', 'Mahdi')
INSERT INTO MECANICIEN (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (102 , CURRENT_TIMESTAMP, 'mecanop', 'mecano', 'ROQUES-GEOFFROY', 'Philippe')

INSERT INTO REVISION (ID, DATE_CREATION, STATUT, ID_MECANICIEN, ID_NAVETTE, ID_QUAI) VALUES (100, TIMESTAMP('2018-01-24 08:00:00'), 'début de révision', 100,1, 97)
INSERT INTO REVISION (ID, DATE_CREATION, STATUT, ID_MECANICIEN, ID_NAVETTE, ID_QUAI) VALUES (101, TIMESTAMP('2018-01-24 08:00:00'), 'début de révision', 101, 7, 83)
INSERT INTO REVISION (ID, DATE_CREATION, STATUT, ID_MECANICIEN, ID_NAVETTE, ID_QUAI) VALUES (102, TIMESTAMP('2018-01-24 08:00:00'), 'fin de révision', 101, 9, 82)
INSERT INTO REVISION (ID, DATE_CREATION, STATUT, ID_NAVETTE, ID_QUAI) VALUES (103, CURRENT_TIMESTAMP, 'révision nécessaire', 1, 97)
INSERT INTO REVISION (ID, DATE_CREATION, STATUT, ID_NAVETTE, ID_QUAI) VALUES (104, CURRENT_TIMESTAMP, 'révision nécessaire', 7, 83)
INSERT INTO REVISION (ID, DATE_CREATION, STATUT, ID_NAVETTE, ID_QUAI) VALUES (105, CURRENT_TIMESTAMP, 'révision nécessaire', 9, 82)
INSERT INTO TRANSFERT (ID, DATE_ARRIVEE, DATE_CREATION, DATE_DEPART, NOMBRE_PASSAGERS, STATUT, ID_CONDUCTEUR, ID_NAVETTE, ID_QUAI_ARRIVE, ID_QUAI_DEPART) VALUES (100, DATE('2018-06-5'), CURRENT_TIMESTAMP, DATE('2018-06-4'), 5, 'transfert initié', 100, 1, 80, 39)
INSERT INTO VOYAGE (ID, DATE_ARRIVEE, DATE_CREATION, DATE_DEPART, NOMBRE_PASSAGERS, STATUT, ID_NAVETTE, ID_QUAI_ARRIVE, ID_QUAI_DEPART, ID_USAGER) VALUES (1, '2018-06-04', '2018-06-02 14:08:02.873', '2018-06-03', 4, 'voyage initié', 9, 40, 20, 100)
