package net.jmhossler.roastd.shoptask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.data.bean.FirebaseRTBeanRepository;
import net.jmhossler.roastd.data.drink.FirebaseRTDrinkRepository;
import net.jmhossler.roastd.data.searchableItem.FirebaseRTSearchableItemRepository;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.shop.FirebaseRTShopRepository;
import net.jmhossler.roastd.data.user.FirebaseRTUserRepository;

import org.w3c.dom.Text;

import java.util.List;

public class ShopActivity extends AppCompatActivity implements ShopContract.View {

  private ShopContract.Presenter mShopPresenter;
  private TextView mName;
  private TextView mDescription;
  private TextView mAddress;
  private TextView mMapsUrl;

  @Override
  public void setPresenter(ShopContract.Presenter shopPresenter) {
    mShopPresenter = shopPresenter;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shop);
    String shopId = getIntent().getStringExtra("SHOP_ID");

    mName = (TextView) findViewById(R.id.shop_name);
    mDescription = (TextView) findViewById(R.id.description);
    mAddress = (TextView) findViewById(R.id.address);
    mMapsUrl = (TextView) findViewById(R.id.maps_url);

    ShopContract.Presenter presenter = new ShopPresenter(this, shopId, FirebaseRTUserRepository.getsInstance(),
      FirebaseRTShopRepository.getInstance(), FirebaseRTSearchableItemRepository.getInstance());
    presenter.setAddress();
    presenter.setName();
    presenter.setDescription();
    presenter.setMapsUrl();
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
}
