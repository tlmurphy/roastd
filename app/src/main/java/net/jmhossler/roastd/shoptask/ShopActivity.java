package net.jmhossler.roastd.shoptask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.jmhossler.roastd.R;

public class ShopActivity extends AppCompatActivity {

  private ShopPresenter mShopPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shop);
  }
}
