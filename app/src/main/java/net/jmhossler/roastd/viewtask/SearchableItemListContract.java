package net.jmhossler.roastd.viewtask;

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
    void setContent(String content);
    void setIcon(byte [] icon);
    void setTag(Object tag);
  }
}
