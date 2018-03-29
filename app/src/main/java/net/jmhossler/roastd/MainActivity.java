package net.jmhossler.roastd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity {

  private Button mSearchButton;
  private Button mProfileButton;

  private static final int RC_SIGN_IN = 9001;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mSearchButton = findViewById(R.id.start_search_activity_button);
    mSearchButton.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), SearchActivity.class)));

    mProfileButton = findViewById(R.id.start_profile_activity_button);
    mProfileButton.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), ProfileActivity.class)));

    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if(account == null) {
      startLogin();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RC_SIGN_IN) {
      if(resultCode != RESULT_OK) {
        startLogin();
      }
    }
  }

  private void startLogin() {
    startActivityForResult(new Intent(this, LoginActivity.class), RC_SIGN_IN);
  }
}
