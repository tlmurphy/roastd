package net.jmhossler.roastd.data.searchableItem;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Description: Superclass for Bean/Shop/Drink to demonstrate their relationships.
public abstract class SearchableItem {
  @NonNull
  private UUID uuid;

  @NonNull
  private String name;

  @Nullable
  private String description;

  @Nullable
  private byte[] image;

  @NonNull
  private ArrayList<String> reviewIds;

  public SearchableItem() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public SearchableItem(@NonNull UUID uuid, @NonNull String name,
                        @Nullable String description, @Nullable byte[] image,
                        @NonNull ArrayList<String> reviewIds) {
    this.setUuid(uuid);
    this.setName(name);
    this.setDescription(description);
    this.setImage(image);
    this.setReviewIds(reviewIds);
  }

  @NonNull
  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(@NonNull UUID uuid) {
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
  public List<String> getReviewIds() {
    return reviewIds;
  }

  public void setReviewIds(@NonNull ArrayList<String> reviewIds) {
    this.reviewIds = reviewIds;
  }
}
