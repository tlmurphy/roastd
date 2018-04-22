package net.jmhossler.roastd.searchtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.util.ActivityUtils;

/**
 * Activity to handle searching
 */
public class SearchActivity extends AppCompatActivity {

  private SearchPresenter mSearchPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    SearchFragment searchFragment =
      (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.searchFrame);
    if(searchFragment == null) {
      searchFragment = SearchFragment.newInstance();
      ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), searchFragment, R.id.searchFrame);
    }

    mSearchPresenter = new SearchPresenter(searchFragment);
  }


}
