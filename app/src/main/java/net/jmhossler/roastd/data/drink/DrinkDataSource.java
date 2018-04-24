package net.jmhossler.roastd.data.drink;


import java.util.List;
import net.jmhossler.roastd.data.searchableItem.BaseDataSource;

public interface DrinkDataSource extends BaseDataSource {

  interface LoadDrinksCallback {

    void onDrinksLoaded(List<Drink> drinks);
    void onDataNotAvailable();
  }

  void getDrinks(LoadDrinksCallback callback);

  void saveDrink(Drink drink);
}
