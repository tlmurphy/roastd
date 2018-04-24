package net.jmhossler.roastd.data.shop;

import java.util.List;
import net.jmhossler.roastd.data.searchableItem.BaseDataSource;

public interface ShopDataSource extends BaseDataSource {

  interface LoadShopsCallback {

    void onShopsLoaded(List<Shop> shops);

    void onDataNotAvailable();
  }

  void getShops(LoadShopsCallback callback);

  void saveShop(Shop shop);

  void deleteShop(String shopId);
}
