# Projet Cloud

Projet d'appli cloud, M2-DevOps

[![Build-and-Push-to-Container-Registry](https://github.com/cripsoo/projetCloud/actions/workflows/buildPackage.yml/badge.svg)](https://github.com/cripsoo/projetCloud/actions/workflows/buildPackage.yml)

[Lien de la prod](https://prod.groupe6.froissant.work) \
[Lien de la recette](https://recette.groupe6.froissant.work)

## Démarrage

Le projet inclut des images docker pour faire entièrement tourner le projet en local (images dev), ainsi qu'un conteneur d'outils avec Azure CLI, kubectl etc ... Il suffit de suivre l'installation suivante.

### Installation

Installation de l'environnement local :

```bash
docker compose -f .\docker-compose-dev.yaml build
docker compose -f .\docker-compose-dev.yaml up -d
```

Et pour le conteneur outils, contenant Azure CLI, kubectl etc...

```bash
cd outils/
docker compose -f .\docker-compose.yaml build
docker compose -f .\docker-compose.yaml up -d
```

Ces conteneurs font directement tourner le projet. \
Voici les URL locales:
- [Frontend](http://localhost:3000)
- [Backend](http://localhost:8080)
- [Swagger](http://localhost:8080/swagger-ui/)

Pour relancer le back par exemple, il suffit de relancer le conteneur.

## Exécution des tests

Pour faire tourner les tests unitaires du back, il suffit de lancer le pipeline dédié ou de faire :
```bash
mvn test
```

## Sonarqube

Voici le lien de [Sonarqube](https://sonarqube-tgpnem3wmwstq.azurewebsites.net/projects/create) : 
* Login : admin 
* Mot de passe : XhFeBiA19emoSJj

## Déploiement

Le projet se déploie sur Azure Cloud depuis les pipelines Github Actions. \
Cela utilise Terraform, Helm, Kubectl etc...

## Construit avec

* [Spring Boot](https://spring.io/guides/gs/spring-boot/) - Framework backend
* [Vue.js](https://vuejs.org/guide/quick-start) - Framework frontend
* [Maven](https://maven.apache.org/) - Gestion des dépendances backend
* [npm](https://www.npmjs.com/) - Gestion des dépendances frontend

## Versionnage

Nous utilisons [SemVer](http://semver.org/) pour le versionnage. Pour voir les versions disponibles, consultez les tags de ce référentiel [ici](https://github.com/cripsoo/projetCloud/tags).

## Diagramme architectural détaillé de l'application:

[Diagramme](https://cdn.discordapp.com/attachments/1157209192080363561/1193650886224904292/Diagramme_DevOps.png?ex=65ad7d04&is=659b0804&hm=f907afc03c1a3db8ce9361c474e122916757f8d4df9545ccc64145711812e30e&)
