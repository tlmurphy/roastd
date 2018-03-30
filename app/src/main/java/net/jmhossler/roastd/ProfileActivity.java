package net.jmhossler.roastd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.bumptech.glide.Glide;

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
    Log.d("GOOGLE","Display Name is: " + User.getUsername(this));
    Glide.with(this).load(User.getPhotoUrl(this))
      .thumbnail(0.5f)
      .into(profilePic);
    username.setText(User.getUsername(this));
    email.setText(User.getEmail(this));

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
    // TODO: Implement apply manager activity
  }
}
