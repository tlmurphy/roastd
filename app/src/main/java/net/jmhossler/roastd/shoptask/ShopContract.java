package net.jmhossler.roastd.shoptask;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;

import java.util.List;

public interface ShopContract {
  interface View extends BaseView<Presenter> {
    void displayName(String name);
    void displayDescription(String description);
    void displayAddress(String address);
    void displayImage(String imageUrl);
    void createMapsLink(String url);
    void displayRating(float score);
    void finish();
  }

  interface Presenter extends BasePresenter {
    void setConsumables(List<String> ids);
    void setName(String name);
    void setImage(String imageUrl);
    void setDescription(String description);
    void setAddress(String address);
    void setMapsUrl(String address);
    void setCurrentRating(SearchableItem item);
    void setNewRating(float rating);
  }
}
