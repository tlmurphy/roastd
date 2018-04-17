package net.jmhossler.roastd.applicationtask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.santalu.maskedittext.MaskEditText;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import net.jmhossler.roastd.R;
import net.jmhossler.roastd.data.ShopApplicationInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * UI for manager application screen
 */
public class ApplicationFragment extends Fragment implements ApplicationContract.View {

  private EditText nameEdit;
  private MaskEditText numEdit;
  private PlacesAutocompleteTextView placesAutocomplete;

  private String shopName;
  private String shopNum;
  private String shopAddress;;

  private ApplicationContract.Presenter presenter;

  public static ApplicationFragment newInstance() {
    Bundle arguments = new Bundle();
    ApplicationFragment fragment = new ApplicationFragment();
    fragment.setArguments(arguments);
    return fragment;
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.application_fragment, container, false);
    setHasOptionsMenu(true);

    // Form fields
    nameEdit = root.findViewById(R.id.shopName);
    numEdit = root.findViewById(R.id.shopNum);
    placesAutocomplete = root.findViewById(R.id.places_autocomplete);
    placesAutocomplete.setOnPlaceSelectedListener(place -> shopAddress = place.description);

    // Submission button
    Button submit = root.findViewById(R.id.applicationSubmit);
    submit.setOnClickListener(v -> presenter.start());
    return root;
  }

  @Override
  public void setPresenter(@NonNull ApplicationContract.Presenter presenter) {
    this.presenter = checkNotNull(presenter);
  }

  @Override
  public void finish() {
    getActivity().finish();
  }

  @Override
  public void showEmptyToast() {
    Toast.makeText(getContext(), "One or more fields are empty, please try again.", Toast.LENGTH_LONG).show();
  }

  @Override
  public ShopApplicationInfo getShopApplicationInfo() {
    shopName = nameEdit.getText().toString();
    shopNum = numEdit.getRawText();
    return new ShopApplicationInfo(shopName, shopNum, shopAddress);
  }
}
