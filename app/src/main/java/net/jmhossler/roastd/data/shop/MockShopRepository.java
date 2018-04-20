package net.jmhossler.roastd.data.shop;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static net.jmhossler.roastd.data.MockDataSourceUtils.deepClone;

public class MockShopRepository implements ShopDataSource {

  private static MockShopRepository sInstance = null;
  private static ArrayList<Shop> sShops;

  private MockShopRepository() {

  }

  public static MockShopRepository getInstance() {
    if (sInstance == null) {
      sInstance = new MockShopRepository();

      URL url;

      try {
        url = new URL("https://goo.gl/maps/GXdVrYPjdk62");
      } catch (MalformedURLException e) {
        e.printStackTrace();
        return sInstance;
      }

      sShops = new ArrayList<>();
      sShops.add(new Shop(UUID.fromString("00000003-0003-0003-0003-000000000003"), "Monarch Espresso Bar",
        "Monarch is a craft coffee shop that celebrates the ordinary. Our place invites" +
          " people as they are to delight in coffee as it should be.",
        "hello world".getBytes(), new ArrayList<>(),
        " 714 22nd Ave, Tuscaloosa, AL 35401", url));
    }
    return sInstance;
  }

  @Override
  public void getShop(UUID shopId, GetShopCallback callback) {
    Optional<Shop> op = sShops.stream()
      .filter(shop -> shop.getUuid() == shopId)
      .findFirst();

    if (op.isPresent()) {
      // Using cloning to try to emulate repository behavior
      callback.onShopLoaded(deepClone(op.get()));
    } else {
      callback.onDataNotAvailable();
    }
  }

  @Override
  public void getShops(LoadShopsCallback callback) {
    // Using cloning to try to emulate repository behavior

    if (sShops.isEmpty()) {
      callback.onDataNotAvailable();
    } else {
      callback.onShopsLoaded(deepClone(sShops));
    }
  }

  @Override
  public void saveShop(Shop shop) {
    int index = getIndexOfShop(shop.getUuid());

    // Using cloning to try to emulate repository behavior
    Shop copy = deepClone(shop);

    if (index == -1) {
      sShops.add(copy);
    } else {
      sShops.set(index, copy);
    }
  }

  @Override
  public void deleteShop(UUID shopId) {
    int index = getIndexOfShop(shopId);

    if (index != -1) {
      sShops.remove(index);
    }
  }

  private int getIndexOfShop(UUID shopId) {
    return IntStream.range(0, sShops.size())
        .filter(i -> sShops.get(i).getUuid() == shopId)
        .findFirst()
        .orElse(-1);
  }
}
