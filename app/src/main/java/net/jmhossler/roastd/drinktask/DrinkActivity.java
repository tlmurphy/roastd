package net.jmhossler.roastd.drinktask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.jmhossler.roastd.R;

public class DrinkActivity extends AppCompatActivity {

  private DrinkPresenter mDrinkPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drink);
  }
}
