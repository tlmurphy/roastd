package net.jmhossler.roastd.data.shop;

import net.jmhossler.roastd.data.searchableItem.BaseDataSource;

import java.util.Map;

public interface ShopDataSource extends BaseDataSource {

  interface LoadShopsCallback {

    void onShopsLoaded(Map<String, Shop> shops);

    void onDataNotAvailable();
  }

  void getShops(LoadShopsCallback callback);

  void saveShop(Shop shop);

  void deleteShop(String shopId);
}
