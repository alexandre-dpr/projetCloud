# Projet Cloud

Projet d'appli cloud, M2-DevOps

[![Etat Build](https://github.com/cripsoo/projetCloud/actions/workflows/buildRecette.yml/badge.svg)](https://github.com/cripsoo/projetCloud/actions/workflows/buildRecette.yml)

## Démarrage

Le projet inclut des images docker pour faire entièrement tourner le projet en dev, ainsi qu'un conteneur d'outils avec Azure CLI, kubectl etc ... Il suffit de suivre l'installation suivante.

### Installation

Installation de l'environnement de dev :

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
- [frontend](http://localhost:3000)
- [backend](http://localhost:8080)
- // A COMPLETER PAR LA SUITE : swagger, sonarQube...

Pour relancer le back par exemple, il suffit de relancer le conteneur.

## Exécution des tests

Pour faire tourner les tests unitaires du back, il suffit de faire :
```bash
mvn test
```

## Déploiement

Le projet se déploie sur Azure Cloud, avec kubernetes. \
// A COMPLETER PAR LA SUITE : Décrire comment le déploimenet se fait en recette / prod

## Construit avec

* [Spring Boot](https://spring.io/guides/gs/spring-boot/) - Framework backend
* [Vue.js](https://vuejs.org/guide/quick-start) - Framework frontend
* [Maven](https://maven.apache.org/) - Gestion des dépendances backend
* [npm](https://www.npmjs.com/) - Gestion des dépendances frontend

## Versionnage

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

// TODO Etablir les conventions de versionnage

## Diagramme architectural détaillé de l'application:

[Diagramme](https://www.figma.com/file/spQDsmKywXDPW3IYwXmuri/Architecture-projet-cloud?type=design&node-id=2%3A10&mode=design&t=krFaZnMfluucCUx5-1)
