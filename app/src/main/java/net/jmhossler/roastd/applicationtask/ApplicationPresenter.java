package net.jmhossler.roastd.applicationtask;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import net.jmhossler.roastd.data.ShopApplicationInfo;

public class ApplicationPresenter implements ApplicationContract.Presenter{

  @NonNull
  private ApplicationContract.View view;
  private ShopApplicationInfo shopInfo;

  public ApplicationPresenter(@NonNull ApplicationContract.View view) {
    this.view = view;
    this.view.setPresenter(this);
  }

  @Override
  public void start() {
    setShopInfo();
    if (goodInput())
      postApplication();
    else
      view.showEmptyToast();
  }

  public boolean goodInput() {
    return !(TextUtils.isEmpty(shopInfo.shopName) || TextUtils.isEmpty(shopInfo.shopNum) || TextUtils.isEmpty(shopInfo.shopAddress));
  }

  public void postApplication() {
    // TODO: Send the application to our backend or something
    view.finish();
  }

  public void setShopInfo() {
    shopInfo = view.getShopApplicationInfo();
  }
}
