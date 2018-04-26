package net.jmhossler.roastd.maintask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import net.jmhossler.roastd.R;
import net.jmhossler.roastd.data.user.FirebaseRTUserRepository;
import net.jmhossler.roastd.util.ActivityUtils;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

  private MainPresenter mMainPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFrame);
    if (mainFragment == null) {
      mainFragment = MainFragment.newInstance();
      ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
        mainFragment, R.id.mainFrame);
    }

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    mMainPresenter = new MainPresenter(mainFragment, firebaseAuth,
      FirebaseRTUserRepository.getsInstance(), Calendar.getInstance());
  }
}
