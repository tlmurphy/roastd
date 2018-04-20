package net.jmhossler.roastd.applicationtask;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;
import net.jmhossler.roastd.data.shopApplicationInfo.ShopApplicationInfo;

public interface ApplicationContract {
  interface View extends BaseView<Presenter> {
    void finish();
    void showEmptyToast();
    ShopApplicationInfo getShopApplicationInfo();
  }

  interface Presenter extends BasePresenter {
    boolean goodInput();
    void postApplication();
  }

}
