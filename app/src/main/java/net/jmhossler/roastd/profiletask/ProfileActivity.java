package net.jmhossler.roastd.profiletask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.util.ActivityUtils;

public class ProfileActivity extends AppCompatActivity {

  private ProfilePresenter mProfilePresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.profileFrame);
    if (profileFragment == null) {
      profileFragment = ProfileFragment.newInstance();
      ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
        profileFragment, R.id.profileFrame);
    }
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestEmail()
      .build();
    GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);

    mProfilePresenter = new ProfilePresenter(profileFragment, firebaseAuth, googleSignInClient);
  }
}
