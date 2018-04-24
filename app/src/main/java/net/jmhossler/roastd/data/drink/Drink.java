package net.jmhossler.roastd.data.drink;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.jmhossler.roastd.data.searchableItem.SearchableItem;

// Description: Data object to encapsulate drink. Adds type (brew type), price, and image
public class Drink extends SearchableItem {
  @Nullable
  private String type;

  @Nullable
  private Double price;

  @NonNull
  private String shopUUID;

  public Drink() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Drink(@NonNull String uuid, @NonNull String name,
               @Nullable String description, @Nullable byte[] image,
               @Nullable String type, @Nullable Double price, @NonNull String shopUUID) {
    super(uuid, name, description, image);
    this.setType(type);
    this.setPrice(price);
    this.setShopUUID(shopUUID);
  }

  @Nullable
  public String getType() {
    return type;
  }

  public void setType(@Nullable String type) {
    this.type = type;
  }

  @Nullable
  public Double getPrice() {
    return price;
  }

  public void setPrice(@Nullable Double price) {
    this.price = price;
  }

  public void setShopUUID(@NonNull String shopUUID) {
    this.shopUUID = shopUUID;
  }
}
