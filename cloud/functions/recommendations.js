const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp();
const recommendations = {};

/**
 * Creates a comparison table data structure to use in creating recommendations
 * @param {Array(users, drinks)} data user and drink data retreived from realtime database
 */
recommendations.buildComparisonTable = (data) => {
  const table = [];
  const drinks = Object.keys(data.drinks);
  const users = Object.keys(data.users).map(key => {
    return Object.assign({}, data.users[key], { id: key });
  });

  users.forEach(user => {
    const favoritesMap = {};
    const favoriteDrinks = user.favoriteDrinks || [];
    table.push([]);

    user.favoriteDrinks.map(drinkId => {
      favoritesMap[drinkId] = 1;
    });

    drinks.forEach(drinkId => {
      table[table.length - 1].push(
        favoritesMap[drinkId] || 0
      );
    });
  });

  return {
    users,
    drinks,
    table
  };
};

/**
 * Fetchs users and drinks from realtime database
 */
recommendations.getData = () => {
  const database = admin.database();
  const fetchData = Promise.all([
    database.ref('users/').once('value'),
    database.ref('drinks/').once('value')
  ]);

  return fetchData
    .then((data) => {
      return {
        shops: data[0].val(),
        drinks: data[1].val()
      };
    });
};

recommendations.computeRecommendations = (tableData) => {
  const { table, users, drinks } = tableData;
  const updates = {};

  // get the count of favorites that are the same
  const countSame = (userOneFavs, userTwoFavs) => {
    let value = 0;
    userOneFavs.forEach((v, i) => {
      if (userTwoFavs[i] === v) value += 1;
    });

    return value;
  };

  // TODO - eventually modify this to build for one user, not all
  users.forEach((user, i) => {
    const counts = [];
    const recommendationIds = [];
    const userRow = table[i];

    // find users with the most similar favorites list
    table.forEach((row, j) => {
      if (j === i) return;
      counts.push({ index: j, value: countSame(userRow, row)});
    });
    counts.sort((a, b) => b.value - a.value);

    // add drinks from similar users that are not already favorited
    counts.forEach((count) => {
      if (recommendationIds.length > 9) return false;
      const currentRow = table[count.index];

      currentRow.forEach((val, i) => {
        if (val && userRow[i] !== val) recommendationIds.push(drinks[i]);
      });
    });

    updates[user.id] = { recommendations: recommendationIds };
  });

  return updates;
};

/**
 * Generates new recommendations for a user when they add a favorite
 * @param {Object} snapshot the updated object
 * @param {Object} context
 */
recommendations.generateRecommendations = (req, res) => {
  recommendations.getData()
    .then(recommendations.buildComparisonTable)
    .then(recommendations.computeRecommendations)
    .catch(console.log);
};

module.exports = recommendations;
