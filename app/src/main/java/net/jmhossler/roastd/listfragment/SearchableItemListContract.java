package net.jmhossler.roastd.listfragment;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;

public interface SearchableItemListContract {

  interface View extends BaseView<Presenter> {
    void notifyDataSetChanged();
  }

  interface Presenter extends BasePresenter {
    void bindViewAtPosition(int position, SearchableListItemView view);
    int itemCount();
  }

  interface SearchableListItemView {
    void setLabel(String label);
    void setIcon(byte [] icon);
  }
}
