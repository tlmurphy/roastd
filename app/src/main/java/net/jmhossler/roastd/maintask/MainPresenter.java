package net.jmhossler.roastd.maintask;

import android.support.annotation.NonNull;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class MainPresenter implements MainContract.Presenter {
  private static final int RC_SIGN_IN = 9001;
  private static final int RC_SIGN_OUT = 9002;
  private static final int LOGGED_OUT = RESULT_OK;

  @NonNull
  private final MainContract.View mMainView;

  public MainPresenter(@NonNull MainContract.View mainView) {
    mMainView = mainView;

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
    } else {
      mMainView.finish();
    }
  }
}
