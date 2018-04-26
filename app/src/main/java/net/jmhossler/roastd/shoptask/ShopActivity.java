package net.jmhossler.roastd.shoptask;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.data.review.FirebaseRTReviewRepository;
import net.jmhossler.roastd.data.searchableItem.FirebaseRTSearchableItemRepository;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.shop.FirebaseRTShopRepository;
import net.jmhossler.roastd.data.user.FirebaseRTUserRepository;
import net.jmhossler.roastd.util.ActivityUtils;

import java.util.List;

public class ShopActivity extends AppCompatActivity implements ShopContract.View {

  private ShopContract.Presenter mShopPresenter;
  private TextView mName;
  private TextView mDescription;
  private TextView mAddress;
  private TextView mMapsUrl;
  private RelativeLayout mAddressBox;
  private RatingBar mRatingBar;
  private ImageView mImageView;

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
    mAddressBox = findViewById(R.id.address_box);
    mMapsUrl = (TextView) findViewById(R.id.maps_url);
    mRatingBar = findViewById(R.id.rating_bar);
    mImageView = findViewById(R.id.shop_background);

    ShopContract.Presenter presenter = new ShopPresenter(this, shopId, FirebaseRTUserRepository.getsInstance(),
      FirebaseRTShopRepository.getInstance(), FirebaseRTSearchableItemRepository.getInstance(),
      FirebaseRTReviewRepository.getInstance(), FirebaseAuth.getInstance());

    presenter.start();

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
  public void createMapsLink(String address) {
    final String url;
    if (address.equals("")) {
      url = ActivityUtils.getMapsLocationUrl("751 Campus Dr W, Tuscaloosa, AL 35404");
    } else {
      url = ActivityUtils.getMapsLocationUrl(address);
    }
    mAddressBox.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))));
  }

  @Override
  public void displayConsumeables(List<SearchableItem> items) {

  }

  @Override
  public void displayImage(String imageUrl) {
    Glide.with(this).load(imageUrl).thumbnail(0.5f).into(mImageView);
  }

  @Override
  public void displayRating(int score) {
    mRatingBar.setRating(score);
  }
}
