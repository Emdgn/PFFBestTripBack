# PFFBestTripBack

# Configuration de Jenkins
Jenkins est utilisés pour les tests. L'utilisation de Jenkins nécessite plusieurs étapes :
1. Configurer un projet Pipeline. Lier le projet Pipeline au projet GitHub.
2. Générer un webhook à l'aide de ngrok et l'ajouter dans GitHub.
3. Configurer le webhook pour le déclencher à chaque push.
4. Créer le fichier Jenkinsfile.
5. Lancer un premier build manuellement sur Jenkins.
