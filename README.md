# ProjetSpacelib

Prévu pour JEE 7, gestion de projets avec Maven, déploiement sur serveur Glassfish 4.1 ou 5.0

IDE Netbeans : https://netbeans.org/downloads/index.html Prendre la version “Java EE” - Ne fonctionne pas avec Java 9

Accès à l'interface de Glassfish : http://localhost:4848/common/index.jsf

Conception UML du projet : https://drive.google.com/file/d/1qZ4POA0UISNeZ_q_KLFKjG9F2Pv1PqXq/view?usp=sharing

Sujet du projet : https://drive.google.com/file/d/14aA95lMoUG3dPfSQUOpizWIsefNlIN3x/view?usp=sharing

Accès direct au Discord du groupe : https://discord.gg/CP8Qka3

Tutoriel complet d'installation JEE : https://github.com/Wiiz83/ProjetSpacelib/wiki/Installation-compl%C3%A8te


**Voir Wiki pour plus d'info**



# Installation

##  Vérifier / Créer la BD : 
* nom = SpacelibDB
* login = SpacelibDB
* mdp = SpacelibDB

## Clean & Build dans l'ordre
* Vérifier que votre serveur GlassFish est arreté 
* Clean & Build l'application JAVA "SpacelibShared"
* Clean & Build l'application d'entreprise "Spacelib"
* Clean & Build les clients web et lourds
* Lancer le serveur GlassFish 

## Tester
* Webservice : "http://localhost:8080/MIAGEBank/MIAGEBank-web/WebServicesClientFinaux?wsdl" par exemple
