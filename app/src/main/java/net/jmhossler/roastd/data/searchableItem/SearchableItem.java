package net.jmhossler.roastd.data.searchableItem;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

// Description: Superclass for Bean/Shop/Drink to demonstrate their relationships.
public abstract class SearchableItem {
  @NonNull
  private String uuid;

  @NonNull
  private String name;

  @Nullable
  private String description;

  @Nullable
  private byte[] image;

  @NonNull
  private Map<String, Boolean> reviewIds;

  public SearchableItem() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public SearchableItem(@NonNull String uuid, @NonNull String name,
                        @Nullable String description, @Nullable byte[] image) {
    this.setUuid(uuid);
    this.setName(name);
    this.setDescription(description);
    this.setImage(image);
    this.reviewIds = new HashMap<>();
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
  public Map getReviewIds() {
    return reviewIds;
  }

  public void setReviewIds(@NonNull Map reviewIds) {
    this.reviewIds = reviewIds;
  }

  public void addReviewId(@NonNull String review) {
    this.reviewIds.put(review, true);
  }

  public void removeReviewId(@NonNull String review) {
    this.reviewIds.remove(review);
  }
}
