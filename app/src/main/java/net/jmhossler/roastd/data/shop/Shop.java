package net.jmhossler.roastd.data.shop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.jmhossler.roastd.data.searchableItem.SearchableItem;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

// Description: Data object to encapsulate shop. Adds address and googleMapsUrl
public class Shop extends SearchableItem implements Serializable {
  // We had state and zip here. Were we planning on using those for filtering based on location?
  //    I think if we have the coordinates we can filter based on distance, and I saw address as
  //    something we display to the user. Idk, lmk
  @NonNull
  private String address;

  // I am not sure what type should be here. I am having trouble finding examples
  //    of opening default map app and what is needed to pass into it. Putting string for now
  @NonNull
  private URL googleMapsUrl;

  public Shop() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Shop(@NonNull UUID uuid, @NonNull String name,
              @Nullable String description, @Nullable byte[] image,
              @NonNull ArrayList<String> reviewIds,
              @NonNull String address, @NonNull URL googleMapsUrl) {
    super(uuid, name, description, image, reviewIds);
    this.setAddress(address);
    this.setGoogleMapsUrl(googleMapsUrl);
  }

  @NonNull
  public String getAddress() {
    return address;
  }

  public void setAddress(@NonNull String address) {
    this.address = address;
  }

  @NonNull
  public URL getGoogleMapsUrl() {
    return googleMapsUrl;
  }

  public void setGoogleMapsUrl(@NonNull URL googleMapsUrl) {
    this.googleMapsUrl = googleMapsUrl;
  }
}
