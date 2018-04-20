package net.jmhossler.roastd.data.drink;

import android.util.Base64;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static net.jmhossler.roastd.data.MockDataSourceUtils.deepClone;

public class MockDrinkRepository implements DrinkDataSource {

  private static MockDrinkRepository sInstance = null;
  private static ArrayList<Drink> sDrinks;

  private MockDrinkRepository() {

  }

  public static MockDrinkRepository getInstance() {
    if (sInstance == null) {
      sInstance = new MockDrinkRepository();
      sDrinks = new ArrayList<>();
      sDrinks.add(new Drink(UUID.fromString("2-2-2-2-2"), "Ristretto",
        "Ristretto is traditionally a short shot of espresso made with the normal amount" +
          " of ground coffee but extracted with about half the amount of water. Since ristrettos are" +
          " essentially the first half of a full-length extraction, the faster-to-extract compounds " +
          "predominate in a ristretto.",
        Base64.encode("hello john".getBytes(), Base64.DEFAULT), new ArrayList<>(), "espresso", new BigDecimal(1)));
    }
    return sInstance;
  }

  @Override
  public void getDrink(UUID drinkId, GetDrinkCallback callback) {
    Optional<Drink> op = sDrinks.stream()
      .filter(drink -> drink.getUuid() == drinkId)
      .findFirst();

    if (op.isPresent()) {
      // Using cloning to try to emulate repository behavior
      callback.onDrinkLoaded(deepClone(op.get()));
    } else {
      callback.onDataNotAvailable();
    }
  }

  @Override
  public void getDrinks(LoadDrinksCallback callback) {
    // Using cloning to try to emulate repository behavior
    callback.onDrinksLoaded(deepClone(sDrinks));
  }

  @Override
  public void saveDrink(Drink drink) {
    int index =
      IntStream.range(0, sDrinks.size())
        .filter(i -> sDrinks.get(i).getUuid() == drink.getUuid())
        .findFirst()
        .orElse(-1);

    // Using cloning to try to emulate repository behavior
    Drink copy = deepClone(drink);

    if (index == -1) {
      sDrinks.add(copy);
    } else {
      sDrinks.set(index, copy);
    }
  }
}
