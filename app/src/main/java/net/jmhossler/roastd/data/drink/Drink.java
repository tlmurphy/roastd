package net.jmhossler.roastd.data.drink;

import net.jmhossler.roastd.data.searchableItem.SearchableItem;

import java.util.Map;

// Description: Data object to encapsulate drink. Adds type (brew type), price, and image
public class Drink extends SearchableItem {
  private String type;
  private Double price;

  public Drink() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Drink(String uuid,  String name, String description,  String imageUrl, String type,
               Double price,  String shopUUID, Map<String, Boolean> reviewIds) {
    super(uuid, name, description, imageUrl, reviewIds, shopUUID);
    setType(type);
    setPrice(price);
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
