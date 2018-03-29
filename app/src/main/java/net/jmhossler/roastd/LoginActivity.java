package net.jmhossler.roastd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

/**
 * Activity to handle Google login.
 */
public class LoginActivity extends AppCompatActivity {

  private static final String TAG = "LoginActivity";
  private static final int RC_SIGN_IN = 9001;

  private GoogleSignInClient mGoogleSignInClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    // Button listener
    findViewById(R.id.sign_in_button).setOnClickListener(v -> signIn());

    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestEmail()
      .build();

    // Build a GoogleSignInClient with the options specified by gso.
    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    // Set the dimensions of the sign-in button.
    SignInButton signInButton = findViewById(R.id.sign_in_button);
    signInButton.setSize(SignInButton.SIZE_STANDARD);
    signInButton.setColorScheme(SignInButton.COLOR_DARK);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
      // The Task returned from this call is always completed, no need to attach
      // a listener.
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
      handleSignInResult(task);
    }
  }

  private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try {
      GoogleSignInAccount account = completedTask.getResult(ApiException.class);
      User.setUsername(this, account.getDisplayName());
      String photoUrl;
      try {
        photoUrl = account.getPhotoUrl().toString();
      } catch (NullPointerException np) {
        Log.d("GOOGLE", "Account did not contain a photo URL.");
        photoUrl = "";  // TODO: Maybe use a placeholder profile pic?
      }
      User.setPhotoUrl(this, photoUrl);
      User.setEmail(this, account.getEmail());
      finish();
      startActivity(new Intent(getApplicationContext(), MainActivity.class));
    } catch (ApiException e) {
      // The ApiException status code indicates the detailed failure reason.
      // Please refer to the GoogleSignInStatusCodes class reference for more information.
      Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
      finish();
      startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
  }

  private void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }
}
