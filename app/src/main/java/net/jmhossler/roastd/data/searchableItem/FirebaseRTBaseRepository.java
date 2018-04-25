package net.jmhossler.roastd.data.searchableItem;

import android.util.Log;

import java.util.HashMap;

public abstract class FirebaseRTBaseRepository implements BaseDataSource {

  private static final String TAG = "FirebaseRTBaseRepository";

  protected <T extends SearchableItem> T populateNullFields(T si) {
    if (si.getUuid() == null) {
      Log.e(TAG, "Error: " + si.getClass().getName() + " UUID is null!");
    }
    if (si.getName() == null) {
      si.setName("");
    }
    if (si.getDescription() == null) {
      si.setDescription("");
    }
    if (si.getImage() == null) {
      si.setImage("");
    }
    if (si.getReviewIds() == null) {
      si.setReviewIds(new HashMap<>());
    }
    if (si.getShopUUID() == null) {
      si.setShopUUID("");
    }
    return si;
  }
}
