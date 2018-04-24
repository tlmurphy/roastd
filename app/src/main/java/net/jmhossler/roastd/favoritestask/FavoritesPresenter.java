package net.jmhossler.roastd.favoritestask;

import net.jmhossler.roastd.data.drink.Drink;
import net.jmhossler.roastd.data.drink.DrinkDataSource.LoadDrinksCallback;
import net.jmhossler.roastd.data.drink.DrinkDataSource;
import net.jmhossler.roastd.data.drink.FirebaseRTDrinkRepository;
import net.jmhossler.roastd.viewtask.BaseSearchableItemPresenter;
import net.jmhossler.roastd.viewtask.SearchableItemListContract;

import java.util.List;

public class FavoritesPresenter extends BaseSearchableItemPresenter {

  public FavoritesPresenter(SearchableItemListContract.View v) {
    super(v);
    mListView.setPresenter(this);

    // example change this to favorites using john's wrapper
    DrinkDataSource ds = FirebaseRTDrinkRepository.getInstance();

    ds.getDrinks(new LoadDrinksCallback() {
      @Override
      public void onDrinksLoaded(List<Drink> drinks) {
        mItems.addAll(drinks);
        mListView.notifyDataSetChanged();
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }
}
