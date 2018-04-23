package net.jmhossler.roastd.viewtask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import net.jmhossler.roastd.data.dummy.DummyContent;
import net.jmhossler.roastd.R;


public class SearchableItemListFragment extends Fragment implements SearchableItemListContract.View {

  private RecyclerView mListRecyclerView;
  private SearchableItemAdapter mSearchableItemAdapter;
  private SearchableItemListContract.Presenter mPresenter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.item_list, container, false);

    mListRecyclerView = v.findViewById(R.id.item_list);

    // The RecyclerView does very little. The LayoutManager is the one who positions items
    // on the screen and defines how scrolling works.
    mListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mSearchableItemAdapter = new SearchableItemAdapter(mPresenter);
    mListRecyclerView.setAdapter(mSearchableItemAdapter);
    return v;
  }

  @Override
  public void onStart() {
    super.onStart();
    mPresenter.start();
  }

  @Override
  public void setPresenter(SearchableItemListContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void notifyDataSetChanged() {
    mSearchableItemAdapter.notifyDataSetChanged();
  }

  /// Holds the View of each item in the RecyclerView
  class SearchableItemHolder extends RecyclerView.ViewHolder implements
    SearchableItemListContract.SearchableListItemView {

    final ImageView mIconImageView;
    final TextView mContentView;

    public SearchableItemHolder(LayoutInflater inflater, ViewGroup parent) {
      super(inflater.inflate(R.layout.item_list_content, parent, false));
      mIconImageView = itemView.findViewById(R.id.list_item_icon);
      mContentView = itemView.findViewById(R.id.content);

    }

    @Override
    public void setContent(String content) {
      mContentView.setText(content);
    }

    @Override
    public void setTag(Object tag) {
      super.itemView.setTag(tag);
    }

    @Override
    public void setIcon(byte[] icon) {
      // we can deal with this later
    }
  }

  // RecyclerView calls this adapter to retrieve new ViewHolders and to bind data
  // to view holders. Binding data happens a lot more often, since the RecyclerView
  // just reuses only as many ViewHolders (and thus views) as can be seen on the screen.
  // When a view "goes off screen" another (already created) view is just bound to new data
  private class SearchableItemAdapter extends RecyclerView.Adapter<SearchableItemHolder> {

    private SearchableItemListContract.Presenter mPresenter;
    private final View.OnClickListener mOnClickListener = view -> {
      DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();

      Context context = view.getContext();
      Intent intent = new Intent(context, SearchableItemActivity.class);
      intent.putExtra(SearchableItemFragment.ARG_ITEM_ID, item.id);

      context.startActivity(intent);
    };


    public SearchableItemAdapter(SearchableItemListContract.Presenter presenter) {
      mPresenter = presenter;
    }

    @NonNull
    @Override
    public SearchableItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(getActivity());
        return new SearchableItemHolder(inf, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchableItemHolder holder, int position) {
      mPresenter.bindViewAtPosition(position, holder);
      holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
      return mPresenter.itemCount();
    }
  }
}
