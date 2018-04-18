package net.jmhossler.roastd.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Base64;

// Description: Data object to encapsulate Beans. Adds roastType and origin.
public class Bean extends SearchableItem {

  @NonNull
  public String roastType;

  @Nullable
  public String origin;

  public Bean() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Bean(@NonNull String uuid, @NonNull String name,
              @Nullable String description, @Nullable Base64 image,
              @NonNull String[] reviewIds,
              @NonNull String roastType, @Nullable String origin) {
    super(uuid, name, description, image, reviewIds);
    this.roastType = roastType;
    this.origin = origin;
  }
}
