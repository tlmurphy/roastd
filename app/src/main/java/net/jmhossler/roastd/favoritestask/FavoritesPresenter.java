package net.jmhossler.roastd.favoritestask;

import net.jmhossler.roastd.viewtask.SearchableItemListContract;
import net.jmhossler.roastd.data.dummy.DummyContent;
import java.util.List;

public class FavoritesPresenter implements SearchableItemListContract.Presenter {

  SearchableItemListContract.View mListView;
  private final List<DummyContent.DummyItem> mValues;

  public FavoritesPresenter(SearchableItemListContract.View v) {
    mListView = v;
    mValues = DummyContent.ITEMS;
    mListView.setPresenter(this);


    // DEGENERATE IMPLEMENTATION
    // pretend like we just filled a list or something since the adapter
    // initially assumes we cannot provide any data

    // We would get the user and grab the favorites...
    // Something like...
    /*

    // ...
    private User mCurrentUser
    private
    // ...

    ArrayList<UUID> favorites = mCurrentUser.getFavoriteUuids();

    ArrayList<SearchableItem> items;

    // .. look up items, then use them in the bind functions

    MockUserRepository.getInstance().getUser("refe", new UserDataSource.GetUserCallback() {
      @Override
      public void onUserLoaded(User user) {
        currentUser = user;
      }

      @Override
      public void onDataNotAvailable() {
        fuck()
      }
    });
     */
  }

  @Override
  public void bindViewAtPosition(int position, SearchableItemListContract.SearchableListItemView view) {
    // DEGENERATE IMPLEMENTATION
    view.setContent(mValues.get(position).content);
    view.setTag(mValues.get(position));
  }

  @Override
  public int itemCount() {
    // DEGENERATE IMPLEMENTATION
    return mValues.size();
  }

  @Override
  public void start(){
  }
}
