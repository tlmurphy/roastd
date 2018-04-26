package net.jmhossler.roastd.recommendationstask;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.data.searchableItem.FirebaseRTSearchableItemRepository;
import net.jmhossler.roastd.data.user.FirebaseRTUserRepository;
import net.jmhossler.roastd.util.ActivityUtils;
import net.jmhossler.roastd.viewtask.SearchableItemListFragment;

public class RecommendationsActivity extends AppCompatActivity {

  private RecommendationsPresenter mRecommendationsPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recommendations);
    setupListFragment();
  }

  private void setupListFragment() {
    FragmentManager fm = getSupportFragmentManager();

    SearchableItemListFragment silf =
      (SearchableItemListFragment) fm.findFragmentById(R.id.recommendations_list_fragment_container);

    if (silf == null) {
      silf = new SearchableItemListFragment();
      ActivityUtils.addFragmentToActivity(fm, silf, R.id.recommendations_list_fragment_container);
    }

    mRecommendationsPresenter = new RecommendationsPresenter(silf, FirebaseAuth.getInstance(),
      FirebaseRTSearchableItemRepository.getInstance(), FirebaseRTUserRepository.getsInstance());
  }
}
