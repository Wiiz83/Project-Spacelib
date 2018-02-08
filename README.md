# ProjetSpacelib

Spacelib est un service de transports gratuits entre stations spatiales par navettes, que les usagers peuvent venir emprunter pour aller d’une station à l’autre, seuls ou entre amis !

## Objectifs du projet
Vous avez en charge le développement du SI de gestion de Spacelib, et des différentes applications à destinations des différents utilisateurs du système.

Le projet suit une gestion incrémentale, prévue en 4 cycles (le dernier étant optionnel) :
1. Permettre aux usagers de voyager (fonctionnalités de base) ;
2. Proposer la réservation de voyages ;
3. Assurer la présence de navettes et de quais disponibles dans chaque station ;
4. Proposer des fonctionnalités avancées de recommandations de voyages et de traitement du S.I.

Les deux sous-sections suivantes décrivent les généralités de l’entreprise. Les sections 2 à 5
décrivent les différentes versions à livrer à chaque cycle du développement. Enfin, une annexe en fin de
document présente une liste de stations spatiales utilisables pour le projet.


## Description du parc matériel de Spacelib
Toutes les navettes sont de la même gamme :
* Elles utilisent un système de propulsion par fusion à froid, le carburant n’étant donc pas une préoccupation
* Elles ont les mêmes caractéristiques physiques, et parcourent donc une distance en un même temps.
* En revanche, les navettes n’ont pas toutes le même nombre de places. Les navettes peuvent avoir 2, 5, 10 ou 15 places.

Les stations spatiales sont disséminées dans toute la galaxie :
• Elles ont une localisation (des coordonnées spatiales) ;
• Elles ont un nombre de quais d’arrimages pour les navettes ;
• Chaque quai est identifiable, et tous les quais peuvent accueillir n’importe quelle navette.

## Règles de gestion principales
• Les usagers peuvent librement réserver une navette (et une seule à la fois) pour un voyage.
• Un usager peut emmener des passagers avec lui qui ne sont pas forcement connus du système.
• Une station ne peut pas avoir plus de navettes qu’elle n’a de quais.
• Un quai ne peut arrimer qu’une seule navette à la fois.
• Tous les 3 voyages, une navette doit être révisée dans la station où elle est arrimée. La révision est prise en charge par un mécanicien Spacelib à l’arrivée de la navette dans la station, et la navette est immobilisée à quai jusqu’à ce que le mécanicien ait terminé la révision.
• Une navette est dite disponible si elle est à quai dans une station et qu’elle n’est pas en révision.
• La durée d’un voyage entre deux stations et constante et connue, quelque soient les stations (cf. annexe).
• Un voyage ne peut pas être réalisé si la station de départ n’a pas de navette disponible avec une capacité suffisante pour accueillir l’ensemble des passagers, ou si la station d’arrivée n’a pas de quai libre disponible le jour d’arrivée prévu.
