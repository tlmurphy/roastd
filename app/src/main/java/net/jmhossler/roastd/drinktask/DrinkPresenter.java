package net.jmhossler.roastd.drinktask;


import android.util.Log;

import net.jmhossler.roastd.data.drink.Drink;
import net.jmhossler.roastd.data.drink.DrinkDataSource;
import net.jmhossler.roastd.data.review.ReviewDataSource;
import net.jmhossler.roastd.data.searchableItem.BaseDataSource;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.shop.ShopDataSource;

import static android.support.constraint.Constraints.TAG;

public class DrinkPresenter implements DrinkContract.Presenter {
  private final DrinkDataSource mDrinkDataSource;
  private final ShopDataSource mShopDataSource;
  private final ReviewDataSource mReviewDataSource;
  private DrinkContract.View mView;
  private final String mDrinkId;

  public DrinkPresenter(DrinkContract.View view, String drinkId, DrinkDataSource drinkDataSource,
                        ShopDataSource shopDataSource, ReviewDataSource reviewDataSource) {
    mView = view;
    mView.setPresenter(this);

    mDrinkId = drinkId;
    mDrinkDataSource = drinkDataSource;
    mShopDataSource = shopDataSource;
    mReviewDataSource = reviewDataSource;
  }

  @Override
  public void start() {

  }

  @Override
  public void setShopInfo() {
    // I'm imagining this will start a list fragment with one item
  }

  @Override
  public void setName() {
    mDrinkDataSource.get(mDrinkId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        if (item == null) {
          Log.d(TAG, "Drink id " + mDrinkId + " does not exist");
        }
        String name = item.getName();
        if (name == null) {
          name = "uh oh";
        }
        mView.displayName(name);
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setDescription() {
    mDrinkDataSource.get(mDrinkId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        String description = item.getDescription();
        if (description == null) {
          description = "something bad";
        }
        mView.displayDescription(description);
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setType() {
    mDrinkDataSource.get(mDrinkId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        Drink drink = (Drink) item;
        mView.displayType(drink.getType());
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setPrice() {
    mDrinkDataSource.get(mDrinkId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        Drink drink = (Drink) item;
        mView.displayPrice(drink.getPrice());
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setReviews() {

  }

  @Override
  public void setImage() {

  }
}
