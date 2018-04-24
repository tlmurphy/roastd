package net.jmhossler.roastd.data.bean;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.jmhossler.roastd.data.searchableItem.SearchableItem;


// Description: Data object to encapsulate Beans. Adds roastType and origin.
public class Bean extends SearchableItem {

  @NonNull
  private String roastType;

  @Nullable
  private String origin;

  @NonNull
  public String getShopUUID() {
    return shopUUID;
  }

  @NonNull
  private String shopUUID;

  public Bean() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Bean(@NonNull String uuid, @NonNull String name,
              @Nullable String description, @Nullable byte[] image,
              @NonNull String roastType, @Nullable String origin, @NonNull String shopUUID) {
    super(uuid, name, description, image);
    this.setRoastType(roastType);
    this.setOrigin(origin);
    this.setShopUUID(shopUUID);
  }

  @NonNull
  public String getRoastType() {
    return roastType;
  }

  public void setRoastType(@NonNull String roastType) {
    this.roastType = roastType;
  }

  @Nullable
  public String getOrigin() {
    return origin;
  }

  public void setOrigin(@Nullable String origin) {
    this.origin = origin;
  }

  public void setShopUUID(@NonNull String shopUUID) {
    this.shopUUID = shopUUID;
  }
}
