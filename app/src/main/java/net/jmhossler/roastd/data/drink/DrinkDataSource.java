package net.jmhossler.roastd.data.drink;

import java.util.Map;

public interface DrinkDataSource {

  interface GetDrinkCallback {

    void onDrinkLoaded(Drink bean);

    void onDataNotAvailable();
  }

  interface LoadDrinksCallback {

    void onDrinksLoaded(Map<String, Drink> beans);

    void onDataNotAvailable();
  }

  void getDrink(String drinkId, GetDrinkCallback callback);

  void getDrinks(LoadDrinksCallback callback);

  void saveDrink(Drink drink);
}
