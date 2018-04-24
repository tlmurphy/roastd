package net.jmhossler.roastd.beantask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.jmhossler.roastd.R;

public class BeanActivity extends AppCompatActivity {

  private BeanPresenter mBeanPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bean);


  }
}
