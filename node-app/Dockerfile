# Utilisez une image Node.js comme base
FROM node:14

# Définissez le répertoire de travail dans le conteneur
WORKDIR /front/test

# Installez les dépendances npm
RUN npm install

# Exposez le port 3000 (port par défaut pour les applications React)
EXPOSE 3000

# Commande pour lancer votre application React
CMD ["npm", "start"]
