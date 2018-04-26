package net.jmhossler.roastd.drinktask;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;

import java.util.List;

public interface DrinkContract {
  interface View extends BaseView<Presenter> {
    void displayName(String name);
    void displayDescription(String description);
    void displayShop(String shopId);
    void displayType(String type);
    void displayPrice(Double price);
    void displayReview(List<String> reviewIds);
    void displayImage(String imageUrl);
  }

  interface Presenter extends BasePresenter {
    void setShopInfo();
    void setName();
    void setDescription();
    void setType();
    void setPrice();
    void setReviews();
    void setImage();
  }
}
