package net.jmhossler.roastd.shoptask;

import net.jmhossler.roastd.data.searchableItem.BaseDataSource;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.searchableItem.SearchableItemDataSource;
import net.jmhossler.roastd.data.shop.Shop;
import net.jmhossler.roastd.data.shop.ShopDataSource;
import net.jmhossler.roastd.data.user.UserDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopPresenter implements ShopContract.Presenter {
  private final String mShopId;
  private final UserDataSource mUserDataSource;
  private final ShopContract.View mView;
  private final ShopDataSource mShopDataSource;
  private final SearchableItemDataSource mSearchableItemDataSource;


  public ShopPresenter(ShopContract.View view, String shopId, UserDataSource userDataSource, ShopDataSource shopDataSource,
                       SearchableItemDataSource searchableItemDataSource) {
    mShopId = shopId;
    mUserDataSource = userDataSource;
    mView = view;
    mShopDataSource = shopDataSource;
    mSearchableItemDataSource = searchableItemDataSource;

    mView.setPresenter(this);
  }

  @Override
  public void start() {
  }

  @Override
  public void setConsumeables() {
    mShopDataSource.get(mShopId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        Shop shop = (Shop) item;
        Map<String, Boolean> consumeables = shop.getItemUUIDs();
        if (consumeables == null) {
          consumeables = new HashMap<>();
        }
        mSearchableItemDataSource.getSearchableItems(new ArrayList<>(consumeables.keySet()), new SearchableItemDataSource.LoadSearchableItemsCallback() {
          @Override
          public void onSearchableItemsLoaded(List<SearchableItem> items) {
            mView.displayConsumeables(items);
          }

          @Override
          public void onDataNotAvailable() {

          }
        });
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setName() {
    mShopDataSource.get(mShopId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        String name = item.getName();
        if(name == null) {
          name = "Uh Oh";
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
    mShopDataSource.get(mShopId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        String description = item.getDescription();
        if(description == null) {
          description = "Uh Oh";
        }
        mView.displayDescription(description);
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setAddress() {
    mShopDataSource.get(mShopId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        Shop shop = (Shop) item;
        String address = shop.getAddress();
        if(address == null) {
          address = "Uh Oh";
        }
        mView.displayAddress(address);
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setMapsUrl() {

  }
}
