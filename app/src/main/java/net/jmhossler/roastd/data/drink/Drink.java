package net.jmhossler.roastd.data.drink;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.jmhossler.roastd.data.searchableItem.SearchableItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

// Description: Data object to encapsulate drink. Adds type (brew type), price, and image
public class Drink extends SearchableItem implements Serializable {
  @Nullable
  private String type;

  @Nullable
  private BigDecimal price;

  public Drink() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Drink(@NonNull UUID uuid, @NonNull String name,
               @Nullable String description, @Nullable byte[] image,
               @NonNull ArrayList<String> reviewIds,
               @Nullable String type, @Nullable BigDecimal price) {
    super(uuid, name, description, image, reviewIds);
    this.setType(type);
    this.setPrice(price);
  }

  @Nullable
  public String getType() {
    return type;
  }

  public void setType(@Nullable String type) {
    this.type = type;
  }

  @Nullable
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(@Nullable BigDecimal price) {
    this.price = price;
  }
}
