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
INSERT INTO ADMINISTRATEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (1 , CURRENT_TIMESTAMP, 'adminl', 'admin', 'UZAN', 'Lucas')
INSERT INTO ADMINISTRATEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (2 , CURRENT_TIMESTAMP, 'adminm', 'admin', 'HENTATI', 'Mahdi')
INSERT INTO ADMINISTRATEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (3 , CURRENT_TIMESTAMP, 'adminp', 'admin', 'ROQUES-GEOFFROY', 'Philippe')
INSERT INTO CONDUCTEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (1 , CURRENT_TIMESTAMP, 'conducl', 'conduc', 'UZAN', 'Lucas')
INSERT INTO CONDUCTEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (2 , CURRENT_TIMESTAMP, 'conducm', 'conduc', 'HENTATI', 'Mahdi')
INSERT INTO CONDUCTEUR (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (3 , CURRENT_TIMESTAMP, 'conducp', 'conduc', 'ROQUES-GEOFFROY', 'Philippe')
INSERT INTO MECANICIEN (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (1 , CURRENT_TIMESTAMP, 'mecanol', 'mecano', 'UZAN', 'Lucas')
INSERT INTO MECANICIEN (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (2 , CURRENT_TIMESTAMP, 'mecanom', 'mecano', 'HENTATI', 'Mahdi')
INSERT INTO MECANICIEN (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (3 , CURRENT_TIMESTAMP, 'mecanop', 'mecano', 'ROQUES-GEOFFROY', 'Philippe')
INSERT INTO STATION (ID, LOCALISATION, NOM, NOMBRE_QUAIS, STATUT) VALUES (1 ,'sol.d', 'Terre', 15, 'Disponible')
INSERT INTO STATION (ID, LOCALISATION, NOM, NOMBRE_QUAIS, STATUT) VALUES (2 ,'pegasi.b', 'Dimidium', 15, 'Disponible')
INSERT INTO STATION (ID, LOCALISATION, NOM, NOMBRE_QUAIS, STATUT) VALUES (3 ,'delphini.b', 'Arion', 15, 'Disponible')
INSERT INTO STATION (ID, LOCALISATION, NOM, NOMBRE_QUAIS, STATUT) VALUES (4 ,'cancri.c', 'Brahe', 15, 'Disponible')
INSERT INTO STATION (ID, LOCALISATION, NOM, NOMBRE_QUAIS, STATUT) VALUES (5 ,'epsilonTauri.b', 'Amateru', 15, 'Disponible')
INSERT INTO STATION (ID, LOCALISATION, NOM, NOMBRE_QUAIS, STATUT) VALUES (6 ,'gammaCepheiA.b', 'Tadmor', 15, 'Disponible')
INSERT INTO NAVETTE (ID, NBPLACES, NBVOYAGES, STATUT) VALUES (1, 5, 3, 'Disponible')
INSERT INTO NAVETTE (ID, NBPLACES, NBVOYAGES, STATUT) VALUES (2, 5, 3, 'Disponible')
INSERT INTO NAVETTE (ID, NBPLACES, NBVOYAGES, STATUT) VALUES (3, 5, 3, 'Disponible')
INSERT INTO NAVETTE (ID, NBPLACES, NBVOYAGES, STATUT) VALUES (4, 5, 3, 'Disponible')
INSERT INTO QUAI (ID, STATUT, ID_STATION, ID_NAVETTE) VALUES (1, 'Disponible', 1, 1)
INSERT INTO QUAI (ID, STATUT, ID_STATION, ID_NAVETTE) VALUES (2, 'Disponible', 2, 2)
INSERT INTO QUAI (ID, STATUT, ID_STATION, ID_NAVETTE) VALUES (3, 'Disponible', 3, 3)
INSERT INTO REVISION (ID, DATE_CREATION, STATUT, ID_MECANICIEN, ID_NAVETTE, ID_QUAI) VALUES (1, CURRENT_TIMESTAMP, 'Disponible', 1, 1, 1)
INSERT INTO REVISION (ID, DATE_CREATION, STATUT, ID_MECANICIEN, ID_NAVETTE, ID_QUAI) VALUES (2, CURRENT_TIMESTAMP, 'Disponible', 2, 2, 2)
INSERT INTO REVISION (ID, DATE_CREATION, STATUT, ID_MECANICIEN, ID_NAVETTE, ID_QUAI) VALUES (3, CURRENT_TIMESTAMP, 'Disponible', 3, 3, 3)
INSERT INTO TRANSFERT (ID, DATE_ARRIVEE, DATE_CREATION, DATE_DEPART, NOMBRE_PASSAGERS, STATUT, ID_CONDUCTEUR, ID_NAVETTE, ID_QUAI_ARRIVE, ID_QUAI_DEPART) VALUES (1, TIMESTAMP('2018-09-24 08:00:00'), CURRENT_TIMESTAMP, TIMESTAMP('2018-09-22 08:00:00'), 5, 'Disponible', 1, 1, 2, 1)
INSERT INTO USAGER (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (1 , CURRENT_TIMESTAMP, 'usagerl', 'usager', 'UZAN', 'Lucas')
INSERT INTO USAGER (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (2 , CURRENT_TIMESTAMP, 'usagerm', 'usager', 'HENTATI', 'Mahdi')
INSERT INTO USAGER (ID, DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (3 , CURRENT_TIMESTAMP, 'usagerp', 'usager', 'ROQUES-GEOFFROY', 'Philippe')
INSERT INTO VOYAGE (ID, DATE_ARRIVEE, DATE_CREATION, DATE_DEPART, NOMBRE_PASSAGERS, STATUT, ID_NAVETTE, ID_QUAI_ARRIVE, ID_QUAI_DEPART, ID_USAGER) VALUES (1, TIMESTAMP('2018-09-24 08:00:00'), CURRENT_TIMESTAMP, TIMESTAMP('2018-09-22 08:00:00'), 5, 'Disponible', 1, 2, 1, 1)