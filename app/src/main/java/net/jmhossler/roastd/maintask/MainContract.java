package net.jmhossler.roastd.maintask;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;

public interface MainContract {

  interface View extends BaseView<Presenter> {
    void startLogin();
    boolean needToLogin();
    void finish();
    void displayUserFirstName(String name);
    void displayUserImage(String imageUrl);
    void displayUserFullName(String name);
    void displayGreetingLabel(String greeting);
    String getMorningGreeting();
    String getEveningGreeting();
    String getAfternoonGreeting();

    void enableButtons();

    void disableButtons();
  }

  interface Presenter extends BasePresenter {
    void result(int requestCode, int resultCode);
    void setDisplayName();
    void setFirstName();
    void setCurrentPhotoURL();
    void setGreetingLabel();
  }
}
