package net.jmhossler.roastd.logintask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.util.ActivityUtils;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Activity to handle Google login.
 */
public class LoginActivity extends AppCompatActivity {

  private LoginPresenter mLoginPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    LoginFragment loginFragment =
      (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.loginFrame);
    if(loginFragment == null) {
      loginFragment = LoginFragment.newInstance();
      ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
        loginFragment, R.id.loginFrame);
    }
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    mLoginPresenter = new LoginPresenter(loginFragment, firebaseAuth);
  }
}
