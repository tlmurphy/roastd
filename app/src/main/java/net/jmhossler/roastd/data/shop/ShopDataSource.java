package net.jmhossler.roastd.data.shop;

import java.util.List;
import java.util.UUID;

public interface ShopDataSource {

  interface GetShopCallback {

    void onShopLoaded(Shop shop);

    void onDataNotAvailable();
  }

  interface LoadShopsCallback {

    void onShopsLoaded(List<Shop> shops);

    void onDataNotAvailable();
  }

  void getShop(UUID shopId, GetShopCallback callback);

  void getShops(LoadShopsCallback callback);

  void saveShop(Shop shop);

  void deleteShop(UUID shopId);
}
