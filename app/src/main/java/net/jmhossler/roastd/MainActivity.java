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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if(account == null) startActivity(new Intent(this, LoginActivity.class));
    else {
      setContentView(R.layout.activity_main);
      mSearchButton = findViewById(R.id.start_search_activity_button);
      mSearchButton.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), SearchActivity.class)));

      mProfileButton = findViewById(R.id.start_profile_activity_button);
      mProfileButton.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), ProfileActivity.class)));
    }
  }
}
