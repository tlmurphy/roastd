const functions = require('firebase-functions');
const recommendations = require('./recommendations');

exports.generateRecommendations = functions.https.onRequest(
  recommendations.generateRecommendations
);
