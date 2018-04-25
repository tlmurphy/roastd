package net.jmhossler.roastd.data.searchableItem;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

// Description: Superclass for Bean/Shop/Drink to demonstrate their relationships.
public abstract class SearchableItem {
  private String uuid;
  private String name;
  private String description;
  private byte[] image;

  @NonNull
  private Map<String, Boolean> reviewIds;

  private String shopUUID;

  public SearchableItem() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public SearchableItem(String uuid, String name, String description, byte[] image,
                        Map<String, Boolean> reviewIds, String shopUuid) {
    setUuid(uuid);
    setName(name);
    setDescription(description);
    setImage(image);
    setReviewIds(reviewIds);
    setShopUUID(shopUuid);
  }

  @NonNull
  public String getUuid() {
    return uuid;
  }

  public void setUuid(@NonNull String uuid) {
    this.uuid = uuid;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  @Nullable
  public String getDescription() {
    return description;
  }

  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  @Nullable
  public byte[] getImage() {
    return image;
  }

  public void setImage(@Nullable byte[] image) {
    this.image = image;
  }

  @NonNull
  public Map<String, Boolean> getReviewIds() {
    return reviewIds;
  }

  public void setReviewIds(@NonNull Map<String, Boolean> reviewIds) {
    this.reviewIds = reviewIds;
  }

  public void addReviewId(@NonNull String review) {
    reviewIds.put(review, true);
  }

  public void removeReviewId(@NonNull String review) {
    reviewIds.remove(review);
  }

  public String getShopUUID() {return shopUUID;}

  public void setShopUUID(@NonNull String shopUUID) {
    this.shopUUID = shopUUID;
  }
}
