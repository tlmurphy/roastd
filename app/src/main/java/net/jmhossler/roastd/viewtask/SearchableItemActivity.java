package net.jmhossler.roastd.viewtask;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import net.jmhossler.roastd.R;


public class SearchableItemActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_detail);
    Toolbar toolbar = findViewById(R.id.detail_toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
      .setAction("Action", null).show());

    // Show the Up button in the action bar.
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // savedInstanceState is non-null when there is fragment state
    // saved from previous configurations of this activity
    // (e.g. when rotating the screen from portrait to landscape).
    // In this case, the fragment will automatically be re-added
    // to its container so we don't need to manually add it.
    // For more information, see the Fragments API guide at:
    //
    // http://developer.android.com/guide/components/fragments.html
    //
    if (savedInstanceState == null) {
      // Create the detail fragment and add it to the activity
      // using a fragment transaction.
      Bundle arguments = new Bundle();
      arguments.putString(SearchableItemFragment.ARG_ITEM_ID,
        getIntent().getStringExtra(SearchableItemFragment.ARG_ITEM_ID));
      SearchableItemFragment fragment = new SearchableItemFragment();
      fragment.setArguments(arguments);
      getSupportFragmentManager().beginTransaction()
        .add(R.id.item_detail_container, fragment)
        .commit();
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      // This ID represents the Home or Up button. In the case of this
      // activity, the Up button is shown. For
      // more details, see the Navigation pattern on Android Design:
      //
      // http://developer.android.com/design/patterns/navigation.html#up-vs-back
      //
      navigateUpTo(new Intent(this, SearchableItemListFragment.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}