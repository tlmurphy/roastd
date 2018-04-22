package net.jmhossler.roastd.profiletask;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;

public interface ProfileContract {

  interface View extends BaseView<ProfileContract.Presenter> {

    void setResult(int resultCode);
    void finish();
  }
  interface Presenter extends BasePresenter {

    void signOut();
    String getCurrentUsername();
    String getCurrentEmail();
    String getCurrentPhotoURL();
  }
}
