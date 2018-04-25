package net.jmhossler.roastd.beantask;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;

import java.util.List;


public interface BeanContract {
  interface View extends BaseView<Presenter> {
    void displayName(String name);
    void displayDescription(String description);
    void displayShop(String shopId);
    void displayRoast(String roast);
    void displayOrigin(String origin);
    void displayReview(List<String> reviewIds);
    void displayImage(String imageUrl);
  }

  interface Presenter extends BasePresenter {
    void setShopInfo();
    void setName();
    void setDescription();
    void setRoast();
    void setOrigin();
    void setReview();
    void setImage();
  }
}
