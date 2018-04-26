package net.jmhossler.roastd.viewtask;

import android.graphics.Bitmap;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;

import java.util.List;

public interface SearchableItemListContract {

  interface View extends BaseView<Presenter> {
    void notifyDataSetChanged();
    void notifyItemChanged(int position, Object payload);
    void navigateToBean(String beanId);
    void navigateToDrink(String drinkId);
    void navigateToShop(String shopId);
    void showProgressBarHideList();
    void hideProgressBarShowList();
    void finish();
  }

  interface Presenter extends BasePresenter {
    void onListItemClicked(int position);
    void toggleFavorite(int position, Boolean isFavoriting);
    void bindViewAtPosition(int position, SearchableListItemView view, List<Object> payloads);
    void loadAllImages();
    void cancelImageLoads();
    int itemCount();
    void destroy();
  }

  interface SearchableListItemView {
    void setContent(String content);
    void setFavoriteState(Boolean state);
    void setIcon(Bitmap image);
  }
}
