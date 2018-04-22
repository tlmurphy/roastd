package net.jmhossler.roastd.profiletask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.applicationtask.ApplicationActivity;

/**
 * UI for Profile Screen.
 */
public class ProfileFragment extends Fragment implements ProfileContract.View {

  private ProfileContract.Presenter mPresenter;

  public static ProfileFragment newInstance() { return new ProfileFragment(); }

  public ProfileFragment() {
    // Required empty public constructor
  }

  @Override
  public void setPresenter(ProfileContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onResume() {
    super.onResume();
    mPresenter.start();
  }

  @Nullable
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                           Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.profile_fragment, container, false);

    TextView username = root.findViewById(R.id.username);
    TextView email = root.findViewById(R.id.email);
    ImageView profilePic = root.findViewById(R.id.profilePic);

    username.setText(mPresenter.getCurrentUsername());
    email.setText(mPresenter.getCurrentEmail());
    Glide.with(getContext()).load(mPresenter.getCurrentPhotoURL()).thumbnail(0.5f).into(profilePic);

    Button apply = root.findViewById(R.id.applyButton);
    apply.setOnClickListener(v -> startActivity(new Intent(getContext(), ApplicationActivity.class)));

    Button logout = root.findViewById(R.id.logout);
    logout.setOnClickListener(v -> mPresenter.signOut());

    return root;
  }

  @Override
  public void setResult(int resultCode) {
    getActivity().setResult(resultCode);
  }

  @Override
  public void finish() {
    getActivity().finish();
  }


}
