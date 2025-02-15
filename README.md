# Gestion de Contacts

Ce projet est une application de gestion de contacts développée en Java avec une interface graphique Swing. Elle permet de gérer une liste de contacts en effectuant des opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) via une interface utilisateur intuitive.

## Fonctionnalités

- **Ajouter un contact** : Permet d'ajouter un nouveau contact avec un nom, prénom, téléphone et email.
 
- **Modifier un contact** : Permet de modifier les informations d'un contact existant.

- **Supprimer un contact** : Permet de supprimer un contact de la liste.
  
- **Afficher la liste des contacts** : Affiche tous les contacts enregistrés dans une table.
  

## Technologies utilisées

- **Java** : Langage de programmation principal.
  
- **Swing** : Bibliothèque graphique pour l'interface utilisateur.
  
- **SQLite** : Base de données embarquée pour stocker les contacts.

## Structure du projet

- **`Contact.java`** : Classe modèle représentant un contact.
- **`ContactDAO.java`** : Classe d'accès aux données pour interagir avec la base de données.
- **`DatabaseConnection.java`** : Classe pour gérer la connexion à la base de données SQLite.
- **`ContactManagementUI.java`** : Classe principale de l'interface utilisateur.
- **`Main.java`** : Point d'entrée de l'application.

## Installation

1. **Cloner le dépôt** :
  
   git clone https://github.com/votre-utilisateur/gestion-contacts.git
   
   cd gestion-contacts
   
  ## Configurer la base de données :

Assurez-vous que SQLite est installé sur votre machine.

Le fichier de base de données SQLite est configuré pour être créé à l'emplacement spécifié dans DatabaseConnection.java.

  ## Compiler et exécuter le projet :

Compilez le projet avec javac ou utilisez un IDE comme IntelliJ IDEA ou Eclipse.

Exécutez la classe Main.java pour démarrer l'application.
