package net.jmhossler.roastd.data.shop;

import java.util.Map;

public interface ShopDataSource {

  interface GetShopCallback {

    void onShopLoaded(Shop shop);

    void onDataNotAvailable();
  }

  interface LoadShopsCallback {

    void onShopsLoaded(Map<String, Shop> shops);

    void onDataNotAvailable();
  }

  void getShop(String shopId, GetShopCallback callback);

  void getShops(LoadShopsCallback callback);

  void saveShop(Shop shop);

  void deleteShop(String shopId);
}
