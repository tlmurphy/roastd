package net.jmhossler.roastd.maintask;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;

public interface MainContract {

  interface View extends BaseView<Presenter> {
    void startLogin();
    boolean needToLogin();
    void finish();
  }

  interface Presenter extends BasePresenter {
    void result(int requestCode, int resultCode);
    String getDisplayName();
    String getFirstName();
    String getCurrentPhotoURL();
  }
}
