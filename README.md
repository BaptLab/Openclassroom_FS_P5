# Openclassroom_FS_P5

# Introduction

Projet n°5 de la formation de Développeur Full-Stack - Java et Angular d'Openclassroom (2023/2024).

Il s'agit d'une application fullstack de réservation de cours de yoga. Les comptes administrateurs ont la capacité de créer des sessions et les attribuer à des professeurs répértoriés dans la base de données. Les utilisateurs particuliers peuvent créer un compte utilisateur et une fois connecté, s'inscrire aux sessions disponible depuis leur tableau de bord.

L'application est découpé en deux parties :

1. Front

Le front est divisé en 6 pages :

- Page d'inscription/authentification ;
- Page des sessions de yoga ;
- Page de détail d'une session ;
- Page de creation d'une location (admin) ;
- Page profil utilisateur ;
- Page 'Not Found' : page d'erreur en cas de mauvaise saisie de l'URL.

2. Back

Le back, réalisé à l'aide de Spring, est constitué princialement d'une API qui communique avec une base de données MySQL.

Pièces jointes fournies en amont du projet :

- [Starter code Github](https://github.com/OpenClassrooms-Student-Center/Testez-une-application-full-stack)

Compétence évaluée :

- Rédiger des tests automatiques pour le code en back-end
- Rédiger des tests automatiques pour le code en front-end

# Front

## Installation

Ce projet a été généré avec [Angular CLI](https://github.com/angular/angular-cli) version 14.1.3.

Assurez-vous de l'installer avant de poursuivre avec la commande :

```bash
npm install -g @angular/cli@14.1.3
```

N'oubliez pas d'installer les dépendances en exécutant la commande suivante dans le répertoire du projet :

```bash
npm install
```

## Serveur de Développement

Pour lancer le serveur de développement, utilisez la commande suivante :

```bash
ng serve
```

Accédez à http://localhost:4200/ dans votre navigateur.

Il existe un compte administrateur par default :

- login: yoga@studio.com
- password: test!1234

L'application se rechargera automatiquement si vous apportez des modifications aux fichiers source.

## Lancement des tests

### Test unitaires et d'intégrations

Des tests unitaires et d'intégrations ont été réalisé avec le Framework Jest qui devrait s'installer automatiquement au moment de l'installation des dépendances.

Pour lancer l'ensemble des tests, utilisez la commande suivnate :

```bash
npm run test
```

Si vous souhaitez visualiser la couverture de test, utilisez la commande :

```bash
npm run test:coverage
```

### Tests End-to-End

Des tests End-to-End ont été réalisé avec le framworkd cypress qui devrait s'installer automatique au moment de l'installation des dépendances.

Pour visualiser l'ensemble des tests cypress dans votre navigateur, utilisez la commande :

```bash
npm run e2e
```

Une fenêtre cypress devrait s'ouvrir, choisissez le navigateur pour effectuer les tests et la page des tests devrait aller s'ouvrir dans celui-ci.

Pour visualiser la couverture des tests, il vous suffit, une fois la commande précédentes effectuée, de lancer la suite de tests `main.cy.ts` dans l'application cypress.

Une fois les tests términés, allez dans le dossier `front/coverage/lcov-report` à partir de la racine du projet github et d'ouvrir le fichier `index.html` dans votre navigateur. La couverture de test sera affichée dans votre navigateur.

## Mise en production

Pour construire le projet en vue de la production, utilisez la commande suivante :

```bash
ng build
```

Les artefacts de construction seront stockés dans le répertoire dist/.

# Back

## Installation

Ce projet a été développé en utilisant Java, Spring et MySQL. Assurez-vous d'avoir installé les éléments suivants :

- [Java Development Kit (JDK)](https://www.oracle.com/fr/java/technologies/downloads/) version 8 ou supérieure.
- [MySQL ou MySQL Workbench](https://www.mysql.com/fr/downloads/) installé sur votre machine.
- [Maven](https://maven.apache.org/) installé sur votre machine.

### Installation des dépendances

Avant de lancer l'application, assurez-vous d'installer les dépendances en utilisant Maven. Exécutez la commande suivante dans le répertoire du projet :

```bash
mvn install
```

Cette commande téléchargera et installera toutes les dépendances nécessaires au projet.

### Configuration de la base de données

Créez une base de données MySQL avec le nom de votre choix (nom_de_votre_base_de_données).

Assurez-vous que le fichier application.properties dans le projet Spring contient les informations correctes pour la base de données. Modifiez les paramètres suivants en fonction de votre configuration :

`spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_votre_base_de_données?useSSL=false&serverTimezone=UTC`

`spring.datasource.username=votre_utilisateur`

`spring.datasource.password=votre_mot_de_passe`

Configurez la base de données MySQL en utilisant le fichier sql script.sql présent dans le dossier front-end/ressources/sql.
Vous pouvez utiliser un outil tel que MySQL Workbench pour exécuter ce script.

### Lancement de l'application

Lancez l'application Spring Boot en utilisant la commande Maven suivante dans le terminal :

```bash
mvn spring-boot:run
```

Ou

Lancez l'application Spring Boot depuis votre IDE :

Dans Eclipse : Cliquez avec le bouton droit sur le projet > Run As > Spring Boot App.
Dans IntelliJ : Cliquez sur le bouton Run à côté de la classe principale.

Cela lancera le serveur back-end à l'adresse http://localhost:8080/.

Assurez-vous que le serveur back-end est en cours d'exécution avant de lancer l'application front-end.

## Lancement des tests

### Test unitaires et d'intégrations

Des tests unitaires et d'intégrations ont été réalisé avec le Framework JUnit qui devrait s'installer automatiquement au moment de l'installation des dépendances.

Pour lancer l'ensemble des tests, utilisez la commande suivnate :

```bash
mvn clean test
```

ou

lancer directement les tests depuis votre IDE :

Dans Eclipse : Cliquez avec le bouton droit sur le projet > Run As > Maven Test.

Si vous souhaitez visualiser la couverture de test, une fois la commande précédente effectuée sans erreurs, allez dans le dossier `back/target/site/jacoco` à partir de la racine du projet github et d'ouvrir le fichier `index.html` dans votre navigateur. La couverture de test sera affichée dans votre navigateur.

Attention : la couverture exclut les fichiers suivants :

- `com/openclassrooms/starterjwt/dto/\*\*`
- `com/openclassrooms/starterjwt/models/\*\*`
- `com/openclassrooms/starterjwt/payload/request/\*\*`

Vous pouvez à tout moment modifier ces derniers dans le fichier pom.xml.

## Informations supplémentaires

- Ce projet utilise Angular version 14.2.0 et Spring Boot pour le front-end et le back-end respectivement. Assurez-vous de vérifier la compatibilité lors de mises à jour ou de l'installation de packages supplémentaires.
- N'hésitez pas à personnaliser le projet en fonction de vos besoins spécifiques.
- Pour plus d'informations sur les commandes Angular CLI et la structure du projet, consultez la documentation officielle d'Angular.
- Pour plus d'informations sur Spring Boot, référez-vous à la documentation officielle de Spring Boot : Documentation Spring Boot.

## Licence

Ce projet est sous [licence MIT](https://chat.openai.com/c/LICENSE) - consultez le fichier [LICENSE](https://chat.openai.com/c/LICENSE) pour plus de détails.
