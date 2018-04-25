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
  private String imageUrl;
  private Map<String, Boolean> reviewIds;
  private String shopUUID;

  public SearchableItem() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public SearchableItem(String uuid, String name, String description, String imageUrl,
                        Map<String, Boolean> reviewIds, String shopUuid) {
    setUuid(uuid);
    setName(name);
    setDescription(description);
    setImage(imageUrl);
    setReviewIds(reviewIds);
    setShopUUID(shopUuid);
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage() {
    return imageUrl;
  }

  public void setImage(String image) {
    this.imageUrl = image;
  }

  public Map<String, Boolean> getReviewIds() {
    return reviewIds;
  }

  public void setReviewIds(Map<String, Boolean> reviewIds) {
    this.reviewIds = reviewIds;
  }

  public void addReviewId(String review) {
    reviewIds.put(review, true);
  }

  public void removeReviewId(String review) {
    reviewIds.remove(review);
  }

  public String getShopUUID() {return shopUUID;}

  public void setShopUUID(String shopUUID) {
    this.shopUUID = shopUUID;
  }
}
