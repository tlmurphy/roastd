package net.jmhossler.roastd.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.net.URL;
import java.util.Base64;

// Description: Data object to encapsulate shop. Adds address and googleMapsUrl
public class Shop extends SearchableItem {
  // We had state and zip here. Were we planning on using those for filtering based on location?
  //    I think if we have the coordinates we can filter based on distance, and I saw address as
  //    something we display to the user. Idk, lmk
  @NonNull
  public String address;

  // I am not sure what type should be here. I am having trouble finding examples
  //    of opening default map app and what is needed to pass into it. Putting string for now
  @NonNull
  public URL googleMapsUrl;

  public Shop() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Shop(@NonNull String uuid, @NonNull String name,
              @Nullable String description, @Nullable Base64 image,
              @NonNull String[] reviewIds,
              @NonNull String address, @NonNull URL googleMapsUrl) {
    super(uuid, name, description, image, reviewIds);
    this.address = address;
    this.googleMapsUrl = googleMapsUrl;
  }
}
