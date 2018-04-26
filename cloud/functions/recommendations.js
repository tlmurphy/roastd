const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp();
const recommendations = {};

/**
 * Creates a comparison table data structure to use in creating recommendations
 * @param {Array(users, drinks)} data user and drink data retreived from realtime database
 */
recommendations.buildComparisonTable = (data, userId) => {
  const table = [];
  const users = [];
  const drinks = Object.keys(data.drinks);

  Object.keys(data.users).forEach(key => {
    if (key === userId) return;
    users.push(Object.assign({}, data.users[key], { id: key }));
  });

  users.forEach(user => {
    const favoritesMap = user.favoriteUUIDs || {};
    table.push([]);

    drinks.forEach(drinkId => {
      table[table.length - 1].push(
        favoritesMap[drinkId] || false
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
        users: data[0].val(),
        drinks: data[1].val()
      };
    });
};

recommendations.computeRecommendations = (tableData, user) => {
  const { table, users, drinks } = tableData;
  const favoriteUUIDs = user.favoriteUUIDs || {};

  // get the count of favorites that are the same
  const countSame = (row) => {
    let value = 0;
    row.forEach((v, i) => {
      if (favoriteUUIDs[drinks[i]]) value += 1;
    });

    return value;
  };

  const counts = [];
  const recommendationUUIDs = {};

  // find users with the most similar favorites list
  table.forEach((row, j) => {
    counts.push({ index: j, value: countSame(row)});
  });
  counts.sort((a, b) => b.value - a.value);

  // add drinks from similar users that are not already favorited
  counts.forEach((count) => {
    if (Object.keys(recommendationUUIDs).length > 5) return false;
    if (count.value === 0) return false;

    const currentRow = table[count.index];

    currentRow.forEach((val, i) => {
      if (Object.keys(recommendationUUIDs).length > 5) return false;
      if (!favoriteUUIDs[drinks[i]]) recommendationUUIDs[drinks[i]] = true;
    });
  });

  return recommendationUUIDs;
};

recommendations.saveRecommendations = (recommendationUUIDs, userId) => {
  const ref = admin.database().ref('/users/' + userId);
  return ref.update({ recommendationUUIDs });
};

/**
 * Generates new recommendations for a user when they add a favorite
 * @param {Object} snapshot the updated object
 * @param {Object} context
 */
recommendations.generateRecommendations = (changes, context) => {
  const before = changes.before.val();
  const user = changes.after.val();
  const userId = context.params.userId;

  const countBefore = Object.keys(before.favoriteUUIDs || {}).length;
  const countAfter = Object.keys(user.favoriteUUIDs || {}).length;

  if (countBefore === countAfter) return Promise.resolve();

  return recommendations.getData()
    .then((data) => {
      return recommendations.buildComparisonTable(data, userId);
    })
    .then((tableData) => {
      return recommendations.computeRecommendations(tableData, user);
    })
    .then((recommendationUUIDs) => {
      return recommendations.saveRecommendations(recommendationUUIDs, userId);
    });
};

module.exports = recommendations;
