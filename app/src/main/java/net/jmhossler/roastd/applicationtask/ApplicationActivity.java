package net.jmhossler.roastd.applicationtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import net.jmhossler.roastd.R;
import net.jmhossler.roastd.util.ActivityUtils;

public class ApplicationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_application);

    ApplicationFragment fragment = (ApplicationFragment)
      getSupportFragmentManager().findFragmentById(R.id.applicationFrame);

    if (fragment == null) {
      fragment = ApplicationFragment.newInstance();
      ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
        fragment, R.id.applicationFrame);
    }

    new ApplicationPresenter(fragment);
  }
}
