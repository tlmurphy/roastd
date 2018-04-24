package net.jmhossler.roastd.data.drink;

import net.jmhossler.roastd.data.searchableItem.BaseDataSource;

import java.util.Map;

public interface DrinkDataSource extends BaseDataSource {

  interface LoadDrinksCallback {

    void onDrinksLoaded(Map<String, Drink> drinks);

    void onDataNotAvailable();
  }

  void getDrinks(LoadDrinksCallback callback);

  void saveDrink(Drink drink);
}
