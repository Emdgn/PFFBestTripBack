# PFFBestTripBack

Description
Le projet est la création d’un site internet pour partager des expériences de voyage et proposer des guides pour aider à organiser les prochaines expéditions.
Il y a pour cela différentes catégories d’utilisateurs qui peuvent interagir pour proposer leurs expériences et promouvoir les guides.
Le projet sera accessible sur les différents navigateurs web.

Environnement de développement:
Eclipse IDE pour le développement du backend sous Java et les tests sont réaliser en continu à chaque push sur github avec Jenkins
Visual Studio Code pour le développement du frontend

Technologies utilisées :
Spring : Framework Java pour le développement d'applications
Angular : Plateforme de développement d’applications web
MySQL : Système de gestion de base de données relationnelle



Installation
Cloner les dépôts github :
git clone https://github.com/landazar/PFFBestTripFront.git
git clone https://github.com/Emdgn/PFFBestTripBack.git


Backend
Installer les dépendances Maven :
H2 Database : pour effectuer les tests.
Spring Boot DevTools : permet de mettre à jour le navigateur automatiquement après une modification.
Spring Web : fournit les fonctionnalités d’intégration de base pour la création d’une application web.
Lombok : librairie JAVA (permet de générer les fonctionnalités de base telles que les getter et setter).
Spring Data JPA : librairie (permet de réduire la quantité de code nécessaire)
MySQL Driver : pour pouvoir travailler avec une base de données

Lancer le backend :
mvn spring-boot:run (?)

Le backend est accessible à l’adresse : http://localhost:8080

Pour exécuter le projet, il faut au préalable avoir une database au nom de “pffbesttrip” avec un username “root” et un mot de passe “root”. Au besoin, vous pouvez modifier le username et mot de passe dans le fichier “application.properties”.


Configuration de Jenkins :
Jenkins est utilisé pour les tests. L'utilisation de Jenkins nécessite plusieurs étapes :
1. Configurer un projet Pipeline. Lier le projet Pipeline au projet GitHub.
2. Générer un webhook à l'aide de ngrok et l'ajouter dans GitHub.
3. Configurer le webhook pour le déclencher à chaque push.
4. Créer le fichier Jenkinsfile.
5. Lancer un premier build manuellement sur Jenkins.


Architecture du Projet

Backend

├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.inti/
│   │   │   │	├── PFFBestTripApplication.java	  # Classe principale de l'application
│   │   │   │	└── ServletInitializer.java		    # Classe d’initialisation de la servlet
│   │   │   ├── com.inti.controller/        		# Dossier pour les contrôleurs
│   │   │   │	├── ExperiencesController.java
│   │   │   │	└── …
│   │   │   ├── com.inti.models/             		# Dossier pour les modèles de données
│   │   │   │	├── Experiences.java
│   │   │   │	└── …
│   │   │   └── com.inti.repository/       		  # Dossier pour les repositories
│   │   │   	├── IExperiencesRepository.java
│   │   │   	└── …
│   │   ├── resources/
│   │   	   └── application.properties  		    # Fichier de configuration de l'application
│   └── test/						                        # Dossiers pour les tests
│       └── java/
│	├── com.inti/
│          │     └── PFFBestTripApplicationTests.java               	
│	├── com.inti.controller
│	 │	├──ExperiencesControllerTests.java
│	 │	└── …
│	 └── com.inti.repository	 
│	 	├──ExperiencesRepositoryTests.java
│	 	└── …
├── pom.xml                             			  # Fichier de configuration Maven
├── JenkinsFile                         			  # Configuration du projet jenkins
└── ...





