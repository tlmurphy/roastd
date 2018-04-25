package net.jmhossler.roastd.data.shop;

import net.jmhossler.roastd.data.searchableItem.SearchableItem;

import java.util.Map;

// Description: Data object to encapsulate shop. Adds address and googleMapsUrl
public class Shop extends SearchableItem {
  // We had state and zip here. Were we planning on using those for filtering based on location?
  //    I think if we have the coordinates we can filter based on distance, and I saw address as
  //    something we display to the user. Idk, lmk
  private String address;
  private String googleMapsUrl;
  private Map<String, Boolean> itemUUIDs;

  public Shop() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Shop(String uuid, String name, String description, String imageUrl, String address,
              String googleMapsUrl, Map<String, Boolean> itemUUIDs, Map<String, Boolean> reviewIds) {
    super(uuid, name, description, imageUrl, reviewIds, "");
    setAddress(address);
    setGoogleMapsUrl(googleMapsUrl);
    setItemUUIDs(itemUUIDs);
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGoogleMapsUrl() {
    return googleMapsUrl;
  }

  public void setGoogleMapsUrl(String googleMapsUrl) {
    this.googleMapsUrl = googleMapsUrl;
  }

  public void setItemUUIDs(Map<String, Boolean> itemUUIDs) {
    this.itemUUIDs = itemUUIDs;
  }

  public Map getItemUUIDs() { return itemUUIDs; }

  public void addItemUUID(String item) {
    itemUUIDs.put(item, true);
  }

  public void removeItemUUID(String item) {
    itemUUIDs.remove(item);
  }
}
