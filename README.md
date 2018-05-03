# ProjetSpacelib

Prévu pour JEE 7, gestion de projets avec Maven, déploiement sur serveur Glassfish 4.1 ou 5.0

IDE Netbeans : https://netbeans.org/downloads/index.html Prendre la version “Java EE” - Ne fonctionne pas avec Java 9

Conception UML du projet : https://www.visual-paradigm.com/tutorials/modelinginnetbeans.jsp

Sujet du projet : https://drive.google.com/file/d/14aA95lMoUG3dPfSQUOpizWIsefNlIN3x/view?usp=sharing

Accès direct au Discord du groupe : https://discord.gg/CP8Qka3

Tutoriel complet d'installation JEE : https://github.com/Wiiz83/ProjetSpacelib/wiki/Installation-compl%C3%A8te

**Voir Wiki pour plus d'info**



# Installation

##  Vérifier / Créer la BD : 
* nom = SpacelibDB
* login = SpacelibDB
* mdp = SpacelibDB

Suivre : https://github.com/Wiiz83/ProjetSpacelib/wiki/Installation-compl%C3%A8te#b-cr%C3%A9ation-dune-base-de-donn%C3%A9es-pour-le-back-office

## Clean & Build dans l'ordre
* Vérifier que votre serveur GlassFish est arreté 
* Clean & Build l'application JAVA "SpacelibShared"
* Clean & Build l'application d'entreprise "Spacelib"
* Clean & Build les clients web et lourds
* Lancer le serveur GlassFish 

## Tester
* Accès à l'interface de Glassfish : http://localhost:4848/common/index.jsf
* Interface Web Mécanicien : http://localhost:8080/SpacelibMecanicien/
* Test Web Service Mécanicien : http://localhost:8080/WebServicesMecanicien/WebServicesMecanicien?Tester
