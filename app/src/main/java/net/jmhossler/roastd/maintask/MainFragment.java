package net.jmhossler.roastd.maintask;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.data.searchableItem.FirebaseRTSearchableItemRepository;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.searchableItem.SearchableItemDataSource;
import net.jmhossler.roastd.favoritestask.FavoritesActivity;
import net.jmhossler.roastd.logintask.LoginActivity;
import net.jmhossler.roastd.profiletask.ProfileActivity;
import net.jmhossler.roastd.recommendationstask.RecommendationsActivity;
import net.jmhossler.roastd.searchtask.SearchActivity;
import net.jmhossler.roastd.util.ActivityUtils;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;


/**
 * Main UI for the Main Screen. Users can navigate between locations in the application
 */
public class MainFragment extends Fragment implements MainContract.View {

  private static final int RC_SIGN_OUT = 9002;
  private static final int RC_SIGN_IN = 9001;

  private Button mSearchButton;
  private RelativeLayout mRecommendationsButton;
  private RelativeLayout mFavoritesButton;
  private RelativeLayout mProfile;
  private TextView mGreetingLabel;
  private TextView mNameLabel;
  private TextView mProfileLabel;
  private CircleImageView mProfilePhoto;

  private MainContract.Presenter mPresenter;

  public static MainFragment newInstance() { return new MainFragment(); }

  public MainFragment() {
    // Required empty public constructor
  }

  @Override
  public void setPresenter(@NonNull MainContract.Presenter presenter) {
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

  @Override
  public boolean needToLogin() {
    return ActivityUtils.needToLogin(getContext());
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.main_fragment, container, false);

    mSearchButton = root.findViewById(R.id.start_search_activity_button);
    mSearchButton.setOnClickListener(v -> startActivity(new Intent(getContext(), SearchActivity.class)));

    mProfile = root.findViewById(R.id.profile);
    mProfile.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), ProfileActivity.class), RC_SIGN_OUT));

    mFavoritesButton = root.findViewById(R.id.favorites_button);
    mFavoritesButton.setOnClickListener(v -> startActivity(new Intent(getContext(), FavoritesActivity.class)));

    mRecommendationsButton = root.findViewById(R.id.recommendations_button);
    mRecommendationsButton.setOnClickListener(v -> startActivity(new Intent(getContext(), RecommendationsActivity.class)));

    mGreetingLabel = root.findViewById(R.id.greeting_label);
    mPresenter.setGreetingLabel();

    mNameLabel = root.findViewById(R.id.name_label);
    mPresenter.setFirstName();

    mProfilePhoto = root.findViewById(R.id.profile_image);
    mPresenter.setCurrentPhotoURL();

    mProfileLabel = root.findViewById(R.id.profile_label);
    mPresenter.setDisplayName();

    return root;
  }

  public void startLogin() {
    startActivityForResult(new Intent(getContext(), LoginActivity.class), RC_SIGN_IN);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    mPresenter.result(requestCode, resultCode);
    super.onActivityResult(requestCode, resultCode, data);

  }

  @Override
  public void finish() {
    getActivity().finish();
  }

  @Override
  public void displayUserFirstName(String name) {
    mNameLabel.setText(name + "!");
  }

  @Override
  public void displayUserImage(String imageUrl) {
    Glide.with(getContext()).load(imageUrl).thumbnail(0.5f).into(mProfilePhoto);
  }

  @Override
  public void displayUserFullName(String name) {
    mProfileLabel.setText(name);
  }

  @Override
  public void displayGreetingLabel(String greeting) {
    mGreetingLabel.setText(greeting);
  }

  @Override
  public String getMorningGreeting() {
    return getString(R.string.greeting_morning);
  }

  @Override
  public String getEveningGreeting() {
    return getString(R.string.greeting_evening);
  }

  @Override
  public String getAfternoonGreeting() {
    return getString(R.string.greeting_afternoon);
  }
}
