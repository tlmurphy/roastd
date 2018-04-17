package net.jmhossler.roastd.profiletask;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.bumptech.glide.Glide;
import net.jmhossler.roastd.R;
import net.jmhossler.roastd.applicationtask.ApplicationActivity;

public class ProfileActivity extends AppCompatActivity {

  private GoogleSignInClient mGoogleSignInClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    // Set user info text and user pic
    TextView username = findViewById(R.id.username);
    TextView email = findViewById(R.id.email);
    ImageView profilePic = findViewById(R.id.profilePic);
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getBaseContext());

    if (account == null) {
      finish();
    }

    String name = account.getDisplayName();
    String mEmail = account.getEmail();
    Uri photoURI = account.getPhotoUrl();
    String photoURL;
    if (photoURI == null) {
      // TODO: Find a default photo in case no photo is available. definitely coffee related
      photoURL = "";
    } else {
      photoURL = photoURI.toString();
    }

    Log.d("GOOGLE","Display Name is: " + name);
    Glide.with(this).load(photoURL)
      .thumbnail(0.5f)
      .into(profilePic);
    username.setText(name);
    email.setText(mEmail);

    // Create sign in Client
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestEmail()
      .build();
    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    // Add button listeners
    Button logout = findViewById(R.id.logout);
    logout.setOnClickListener(v -> mGoogleSignInClient.signOut()
      .addOnCompleteListener(this, task -> {
        setResult(RESULT_OK);
        finish();
      }));

    Button apply = findViewById(R.id.applyButton);
    apply.setOnClickListener(v -> startActivity(new Intent(this, ApplicationActivity.class)));
  }
}
