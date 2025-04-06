Application de Gestion de Dépenses

Description

L'Application de Gestion de Dépenses est une application en ligne de commande développée en Java, qui permet aux utilisateurs de suivre et gérer leurs finances personnelles de manière simple et intuitive. Elle permet à l'utilisateur d'enregistrer ses revenus et dépenses, de consulter l'historique de ses transactions, de supprimer une transaction et de suivre l'évolution de son solde. les transactions enregistrer sont stockées dans un fichier txt. ce qui va permettre de pas perdre  les données.

L'application est structurée selon l'architecture MVC (Modèle-Vue-Contrôleur), garantissant une séparation claire des responsabilités et une évolutivité du code.

Fonctionnalités

Gestion des transactions : Enregistrement des revenus et des dépenses avec des informations détaillées telles que la catégorie, la description, le montant et la date.

Historique des transactions : Consultation des transactions passées et de leur répartition.

Calcul du solde : Calcul automatique du solde en fonction des transactions saisies.

Supprimer une transaction lorsqu'il y'a erreur lors de l'renregistrement.

Enregistrer toutes les transactions dans un fichier .txt et les charger au demarrage de l'application.

Interface en ligne de commande : Interaction simple et fluide via une interface textuelle.

Architecture

Le projet utilise l'architecture MVC, séparant ainsi le modèle, la vue et le contrôleur :

Modèle :

Le modèle représente la logique métier et les données, avec des classes comme Transaction pour les opérations financières et FinanceManager pour le calcul du solde.

Utilisation de TransactionRepository pour gérer la persistance des données (ici stockées en mémoire avec TransactionRepositoryImpl).

Vue :

La vue est responsable de l'interaction avec l'utilisateur via la classe ConsoleView. Elle affiche des menus et récupère les entrées utilisateur.

Contrôleur :

Le contrôleur gère l'interaction entre la vue et le modèle via la classe FinanceController. Il orchestre les actions de l'application en fonction des choix de l'utilisateur.

Point d'entrée :

La classe Main sert de point d'entrée de l'application, initialisant tous les composants nécessaires et lançant l'application.

Structure du Projet

📦 Gestion de DEPENSE 
 ┣ 📂 src  
 ┃ ┣ 📂 model  
 ┃ ┃ ┣ Transaction.java  
 ┃ ┃ ┣ TransactionType.java  
 ┃ ┃ ┣ Category.java  
 ┃ ┃ ┗ FinanceManager.java  
 ┃ ┣ 📂 view  
 ┃ ┃ ┗ ConsoleView.java  
 ┃ ┣ 📂 controller  
 ┃ ┃ ┗ FinanceController.java  
 ┃ ┣ 📂 repository  
 ┃ ┃ ┣ TransactionRepository.java  
 ┃ ┃ ┗ FileManager.java (si utilisé)  
 ┃ ┗ TransactionRepositoryImpl.java  
 ┗ Main.java

Évolutivité
Grâce à son architecture modulaire, cette application peut facilement évoluer. Par exemple :

Interface graphique : Remplacer la vue en ligne de commande par une interface graphique (GUI) avec des technologies comme JavaFX ou Swing.

Base de données : Intégrer une base de données (comme MySQL ou SQLite) pour stocker les transactions de manière persistante.

Version mobile : Développer une version mobile de l'application en utilisant un framework comme JavaFX pour Android ou en la réécrivant pour Android Studio.
