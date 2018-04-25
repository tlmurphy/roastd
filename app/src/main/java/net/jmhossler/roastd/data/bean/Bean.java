package net.jmhossler.roastd.data.bean;

import net.jmhossler.roastd.data.searchableItem.SearchableItem;

import java.util.Map;

// Description: Data object to encapsulate Beans. Adds roastType and origin.
public class Bean extends SearchableItem {
  private String roastType;
  private String origin;

  public Bean() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Bean(String uuid, String name, String description,  String imageUrl, String roastType,
              String origin,  String shopUUID, Map<String, Boolean> reviewIds) {
    super(uuid, name, description, imageUrl, reviewIds, shopUUID);
    setRoastType(roastType);
    setOrigin(origin);
  }

  public String getRoastType() {
    return roastType;
  }

  public void setRoastType(String roastType) {
    this.roastType = roastType;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }
}
