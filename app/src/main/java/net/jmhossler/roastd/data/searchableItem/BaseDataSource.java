package net.jmhossler.roastd.data.searchableItem;

public interface BaseDataSource {
  interface GetCallback {
    void onLoaded(SearchableItem item);
    void onDataNotAvailable();
  }

  void get(String id, GetCallback callback);
}
