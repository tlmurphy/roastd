package net.jmhossler.roastd.data.bean;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.jmhossler.roastd.data.searchableItem.SearchableItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

// Description: Data object to encapsulate Beans. Adds roastType and origin.
public class Bean extends SearchableItem implements Serializable {

  @NonNull
  private String roastType;

  @Nullable
  private String origin;

  public Bean() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Bean(@NonNull UUID uuid, @NonNull String name,
              @Nullable String description, @Nullable byte[] image,
              @NonNull ArrayList<String> reviewIds,
              @NonNull String roastType, @Nullable String origin) {
    super(uuid, name, description, image, reviewIds);
    this.setRoastType(roastType);
    this.setOrigin(origin);
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
}
