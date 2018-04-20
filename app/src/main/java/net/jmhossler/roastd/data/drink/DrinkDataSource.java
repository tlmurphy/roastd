package net.jmhossler.roastd.data.drink;

import java.util.List;
import java.util.UUID;

public interface DrinkDataSource {

  interface GetDrinkCallback {

    void onDrinkLoaded(Drink bean);

    void onDataNotAvailable();
  }

  interface LoadDrinksCallback {

    void onDrinksLoaded(List<Drink> beans);

    void onDataNotAvailable();
  }

  void getDrink(UUID drinkId, GetDrinkCallback callback);

  void getDrinks(LoadDrinksCallback callback);

  void saveDrink(Drink drink);
}
