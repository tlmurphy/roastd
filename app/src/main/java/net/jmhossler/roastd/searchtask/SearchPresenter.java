package net.jmhossler.roastd.searchtask;


import android.support.annotation.NonNull;
import android.text.Editable;
import android.util.Log;

public class SearchPresenter implements SearchContract.Presenter {

  private static final String TAG = "SearchActivity";
  @NonNull
  private final SearchContract.View mSearchView;

  public SearchPresenter(@NonNull SearchContract.View searchView) {
    mSearchView = searchView;
    mSearchView.setPresenter(this);
  }

  @Override
  public void start() {

  }

  public void searchForText(Editable searchText) {
    Log.d(TAG, "searchForText " + searchText);
    //add search database here
  }
}
