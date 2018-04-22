package net.jmhossler.roastd.searchtask;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.jmhossler.roastd.R;

public class SearchFragment extends Fragment implements SearchContract.View {

  private SearchContract.Presenter mPresenter;
  private EditText searchText;

  public static SearchFragment newInstance() { return new SearchFragment(); }

  public SearchFragment() {
    // Required empty public constructor
  }

  @Override
  public void setPresenter(@NonNull SearchContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.search_fragment, container, false);
    searchText = root.findViewById(R.id.searchText);
    searchText.addTextChangedListener(new TextWatcher() {
      public void afterTextChanged(Editable s) {
        mPresenter.searchForText(s);
      }
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
      public void onTextChanged(CharSequence s, int start, int before, int count) {}
    });

    return root;
  }

  @Override
  public void onResume() {
    super.onResume();
    mPresenter.start();
  }
}
