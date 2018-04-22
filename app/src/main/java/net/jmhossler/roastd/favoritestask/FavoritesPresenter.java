package net.jmhossler.roastd.favoritestask;

import net.jmhossler.roastd.listfragment.SearchableItemListContract;

public class FavoritesPresenter implements SearchableItemListContract.Presenter {

  SearchableItemListContract.View mListView;

  public FavoritesPresenter(SearchableItemListContract.View v) {
    mListView = v;

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
    view.setLabel("Favorite thing number " + position);
  }

  @Override
  public int itemCount() {
    // DEGENERATE IMPLEMENTATION
    return 40;
  }

  @Override
  public void start(){
  }
}
