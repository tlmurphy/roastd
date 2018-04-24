package net.jmhossler.roastd.data.shop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.jmhossler.roastd.data.searchableItem.SearchableItem;

import java.util.HashMap;
import java.util.Map;

// Description: Data object to encapsulate shop. Adds address and googleMapsUrl
public class Shop extends SearchableItem {
  // We had state and zip here. Were we planning on using those for filtering based on location?
  //    I think if we have the coordinates we can filter based on distance, and I saw address as
  //    something we display to the user. Idk, lmk
  @NonNull
  private String address;

  // I am not sure what type should be here. I am having trouble finding examples
  //    of opening default map app and what is needed to pass into it. Putting string for now
  @NonNull
  private String googleMapsUrl;

  private Map<String, Boolean> itemUUIDs;

  public Shop() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Shop(@NonNull String uuid, @NonNull String name,
              @Nullable String description, @Nullable byte[] image,
              @NonNull String address, @NonNull String googleMapsUrl, @Nullable Map<String, Boolean> itemUUIDs) {
    super(uuid, name, description, image);
    this.setAddress(address);
    this.setGoogleMapsUrl(googleMapsUrl);
    if(itemUUIDs != null) {
      this.setItemUUIDs(itemUUIDs);
    }
    else {
      this.itemUUIDs = new HashMap<>();
    }
  }

  @NonNull
  public String getAddress() {
    return address;
  }

  public void setAddress(@NonNull String address) {
    this.address = address;
  }

  @NonNull
  public String getGoogleMapsUrl() {
    return googleMapsUrl;
  }

  public void setGoogleMapsUrl(@NonNull String googleMapsUrl) {
    this.googleMapsUrl = googleMapsUrl;
  }

  public void setItemUUIDs(@NonNull Map<String, Boolean> itemUUIDs) {
    this.itemUUIDs = itemUUIDs;
  }

  public Map getItemUUIDs() { return this.itemUUIDs; }

  public void addItemUUID(String item) {
    this.itemUUIDs.put(item, true);
  }

  public void removeItemUUID(String item) {
    this.itemUUIDs.remove(item);
  }
}
