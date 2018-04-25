package net.jmhossler.roastd.maintask;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import static android.app.Activity.RESULT_OK;

public class MainPresenter implements MainContract.Presenter {
  private static final int RC_SIGN_IN = 9001;
  private static final int RC_SIGN_OUT = 9002;
  private static final int LOGGED_OUT = RESULT_OK;

  @NonNull
  private final MainContract.View mMainView;
  private FirebaseAuth mAuth;

  public MainPresenter(@NonNull MainContract.View mainView, FirebaseAuth auth) {
    mMainView = mainView;
    mAuth = auth;
    mMainView.setPresenter(this);
  }

  @Override
  public void start() {
    if (mMainView.needToLogin()) {
      mMainView.startLogin();
    }
  }

  @Override
  public void result(int requestCode, int resultCode) {
    if (requestCode == RC_SIGN_OUT && resultCode == LOGGED_OUT) {
      mMainView.startLogin();
    }
  }

  @Override
  public String getDisplayName() {
    if(mAuth.getCurrentUser() == null) {
      return "";
    }
    else {
      return mAuth.getCurrentUser().getDisplayName();
    }
  }

  @Override
  public String getFirstName() {
    if(mAuth.getCurrentUser() == null) {
      return "";
    }
    else {
      return mAuth.getCurrentUser().getDisplayName().split("\\s+")[0];
    }
  }

  @Override
  public String getCurrentPhotoURL() {
    if(mAuth.getCurrentUser() == null) {
      return "";
    }
    else {
      Uri photoURI = mAuth.getCurrentUser().getPhotoUrl();
      if (photoURI == null) {
        return "";
      } else {
        return photoURI.toString();
      }
    }
  }
}
