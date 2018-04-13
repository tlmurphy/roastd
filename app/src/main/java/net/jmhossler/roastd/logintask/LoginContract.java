package net.jmhossler.roastd.logintask;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;

public interface LoginContract {
  interface View extends BaseView<LoginContract.Presenter> {
    void startGoogleSignin();
    void finish();
    void setResult(int resultCode);
    boolean needToLogin();
  }

  interface Presenter extends BasePresenter {
    void signInClicked();
    void result(int requestCode, int resultCode, Intent data);
    void handleSignInResult(Task<GoogleSignInAccount> completedTask);
  }
}
