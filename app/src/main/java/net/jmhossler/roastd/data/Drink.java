package net.jmhossler.roastd.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Base64;

// Description: Data object to encapsulate drink. Adds type (brew type), price, and image
public class Drink extends SearchableItem {
  @Nullable
  public String type;

  @Nullable
  public String price;

  public Drink() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Drink(@NonNull String uuid, @NonNull String name,
               @Nullable String description, @Nullable Base64 image,
               @NonNull String[] reviewIds,
               @Nullable String type, @Nullable String price) {
    super(uuid, name, description, image, reviewIds);
    this.type = type;
    this.price = price;
  }
}
