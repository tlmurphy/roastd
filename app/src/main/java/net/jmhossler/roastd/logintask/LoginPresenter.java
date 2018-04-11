package net.jmhossler.roastd.logintask;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import static android.app.Activity.RESULT_OK;

public class LoginPresenter implements LoginContract.Presenter {

  private static final String TAG = "LoginActivity";
  private static final int RC_SIGN_IN = 9001;

  @NonNull
  private final LoginContract.View mLoginView;

  public LoginPresenter(@NonNull LoginContract.View loginView) {
    mLoginView = loginView;

    mLoginView.setPresenter(this);
  }

  @Override
  public void start() {
    // simply wait on button to be pressed
  }

  public void signInClicked() {
    mLoginView.startGoogleSignin();
  }

  public void result(int requestCode, int resultCode, Intent data) {
    // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
      // The Task returned from this call is always completed, no need to attach
      // a listener.
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
      handleSignInResult(task);
    }
  }

  public void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try {
      completedTask.getResult(ApiException.class);
      mLoginView.setResult(RESULT_OK);
      mLoginView.finish();
    } catch (ApiException e) {
      // The ApiException status code indicates the detailed failure reason.
      // Please refer to the GoogleSignInStatusCodes class reference for more information.
      Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
      mLoginView.startGoogleSignin();
    }
  }
}
