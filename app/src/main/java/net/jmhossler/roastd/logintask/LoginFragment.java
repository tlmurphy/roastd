package net.jmhossler.roastd.logintask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.util.ActivityUtils;

public class LoginFragment extends Fragment implements LoginContract.View {

  private LoginContract.Presenter mPresenter;

  private static final int RC_SIGN_IN = 9001;

  private GoogleSignInClient mGoogleSignInClient;

  public static LoginFragment newInstance() {
    return new LoginFragment();
  }

  public LoginFragment() {
    // Required empty public constructor
  }

  @Override
  public void setPresenter(@NonNull LoginContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestIdToken(getString(R.string.default_web_client_id))
      .requestEmail()
      .build();

    // Build a GoogleSignInClient with the options specified by gso.
    mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.login_fragment, container, false);

    root.findViewById(R.id.sign_in_button).setOnClickListener(v -> mPresenter.signInClicked());

    return root;
  }

  @Override
  public void onResume() {
    super.onResume();
    mPresenter.start();
  }

  @Override
  public void startGoogleSignin() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    mPresenter.result(requestCode, resultCode, data);
    super.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public void setResult(int resultCode) {
    getActivity().setResult(resultCode);
  }

  @Override
  public void finish() {
    getActivity().finish();
  }

  @Override
  public boolean needToLogin() {
    return ActivityUtils.needToLogin(getContext());
  }
}
