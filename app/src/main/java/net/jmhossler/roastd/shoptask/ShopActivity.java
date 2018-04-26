package net.jmhossler.roastd.shoptask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import net.jmhossler.roastd.R;
import net.jmhossler.roastd.data.review.FirebaseRTReviewRepository;
import net.jmhossler.roastd.data.searchableItem.FirebaseRTSearchableItemRepository;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.shop.FirebaseRTShopRepository;
import net.jmhossler.roastd.data.user.FirebaseRTUserRepository;
import java.util.List;

public class ShopActivity extends AppCompatActivity implements ShopContract.View {

  private ShopContract.Presenter mShopPresenter;
  private TextView mName;
  private TextView mDescription;
  private TextView mAddress;
  private TextView mMapsUrl;
  private RatingBar mRatingBar;

  private static final String itemKey = "SHOP_ID";

  public static Intent createIntent(String shopId, Context context) {
    Intent intent = new Intent(context, ShopActivity.class);
    intent.putExtra(itemKey, shopId);
    return intent;
  }

  @Override
  public void setPresenter(ShopContract.Presenter shopPresenter) {
    mShopPresenter = shopPresenter;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shop);
    String shopId = getIntent().getStringExtra(itemKey);

    mName = (TextView) findViewById(R.id.name);
    mDescription = (TextView) findViewById(R.id.description);
    mAddress = (TextView) findViewById(R.id.address);
    mMapsUrl = (TextView) findViewById(R.id.maps_url);
    mRatingBar = findViewById(R.id.rating_bar);

    ShopContract.Presenter presenter = new ShopPresenter(this, shopId, FirebaseRTUserRepository.getsInstance(),
      FirebaseRTShopRepository.getInstance(), FirebaseRTSearchableItemRepository.getInstance(),
      FirebaseRTReviewRepository.getInstance(), FirebaseAuth.getInstance());
    presenter.setAddress();
    presenter.setName();
    presenter.setDescription();
    presenter.setMapsUrl();
    presenter.setCurrentRating();

    mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
      @Override
      public void onRatingChanged(RatingBar ratingBar, float rating,
                                  boolean fromUser) {
        if (fromUser) {
          presenter.setNewRating(rating);
        }
      }
    });
  }

  @Override
  public void displayName(String name) {
    mName.setText(name);
  }

  @Override
  public void displayDescription(String description) {
    mDescription.setText(description);
  }

  @Override
  public void displayAddress(String address) {
    mAddress.setText(address);
  }

  @Override
  public void displayMapsUrl(String url) {
    mMapsUrl.setText(url);
  }

  @Override
  public void displayConsumeables(List<SearchableItem> items) {

  }

  @Override
  public void displayRating(int score) {
    mRatingBar.setRating(score);
  }
}
