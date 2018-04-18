package net.jmhossler.roastd.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Base64;

// Description: Superclass for Bean/Shop/Drink to demonstrate their relationships.
public class SearchableItem {
  @NonNull
  public String uuid;

  @NonNull
  public String name;

  @Nullable
  public String description;

  @Nullable
  public Base64 image;

  @NonNull
  public String[] reviewIds;

  public SearchableItem() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public SearchableItem(@NonNull String uuid, @NonNull String name,
                        @Nullable String description, @Nullable Base64 image,
                        @NonNull String[] reviewIds) {
    this.uuid = uuid;
    this.name = name;
    this.description = description;
    this.image = image;
    this.reviewIds = reviewIds;
  }
}
