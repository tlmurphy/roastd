package net.jmhossler.roastd.drinktask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.beantask.BeanActivity;
import net.jmhossler.roastd.data.drink.FirebaseRTDrinkRepository;
import net.jmhossler.roastd.data.review.FirebaseRTReviewRepository;
import net.jmhossler.roastd.data.shop.FirebaseRTShopRepository;

import java.util.List;

public class DrinkActivity extends AppCompatActivity implements DrinkContract.View {

  private DrinkContract.Presenter mDrinkPresenter;
  private TextView mName;
  private TextView mDescription;
  private TextView mType;
  private TextView mPrice;

  private static final String itemKey = "DRINK_ID";

  public static Intent createIntent(String drinkId, Context context) {
    Intent intent = new Intent(context, DrinkActivity.class);
    intent.putExtra(itemKey, drinkId);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drink);

    String drinkId = getIntent().getStringExtra(itemKey);

    mName = (TextView) findViewById(R.id.name);
    mDescription = (TextView) findViewById(R.id.description);
    mType = (TextView) findViewById(R.id.type);
    mPrice = (TextView) findViewById(R.id.price);

    DrinkPresenter presenter = new DrinkPresenter(this, drinkId, FirebaseRTDrinkRepository.getInstance(),
      FirebaseRTShopRepository.getInstance(), FirebaseRTReviewRepository.getInstance());
    presenter.setName();
    presenter.setDescription();
    presenter.setType();
    presenter.setPrice();
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
  public void displayShop(String shopId) {

  }

  @Override
  public void displayType(String type) {
    mType.setText("Brew: " + type);
  }

  @Override
  public void displayPrice(Double price) {
    mPrice.setText("Price: $" + price.toString());
  }

  @Override
  public void displayReview(List<String> reviewIds) {

  }

  @Override
  public void displayImage(String imageUrl) {

  }

  @Override
  public void setPresenter(DrinkContract.Presenter presenter) {
    mDrinkPresenter = presenter;
  }
}
