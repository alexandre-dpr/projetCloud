// init-db.js
db = db.getSiblingDB('projetCloud');

if (!db.getCollectionNames().includes('user')) {
    db.createCollection('user');
}
