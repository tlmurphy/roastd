package net.jmhossler.roastd.profiletask;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

import static android.app.Activity.RESULT_OK;


public class ProfilePresenter implements ProfileContract.Presenter {

  private static final String TAG = "ProfileActivity";

  @NonNull
  private final ProfileContract.View mProfileView;
  private FirebaseAuth mAuth;
  private GoogleSignInClient mGoogleSignInClient;

 public ProfilePresenter(@NonNull ProfileContract.View profileView, FirebaseAuth auth, GoogleSignInClient googleSignInClient) {
    mProfileView = profileView;
    mAuth = auth;
    mGoogleSignInClient = googleSignInClient;

    mProfileView.setPresenter(this);
  }

  @Override
  public void start() {
  }

  @Override
  public void signOut() {
    mAuth.signOut();
    mGoogleSignInClient.revokeAccess().addOnCompleteListener(task -> {
      mProfileView.setResult(RESULT_OK);
      mProfileView.finish();
    });
  }

  @Override
  public String getCurrentUsername() {
   if(mAuth.getCurrentUser() == null) {
     Log.w(TAG, "No current user");
     return "";
   }
   else {
     return mAuth.getCurrentUser().getDisplayName();
   }
  }

  @Override
  public String getCurrentEmail() {
    if(mAuth.getCurrentUser() == null) {
      Log.w(TAG, "No current user");
      return "";
    }
    else {
      return mAuth.getCurrentUser().getEmail();
    }
  }

  @Override
  public String getCurrentPhotoURL() {
    if(mAuth.getCurrentUser() == null) {
      Log.w(TAG, "No current user");
      return "";
    }
    else {
      Uri photoURI = mAuth.getCurrentUser().getPhotoUrl();
      if (photoURI == null) {
        return "";
      } else {
        return photoURI.toString();
      }
    }
  }
}
