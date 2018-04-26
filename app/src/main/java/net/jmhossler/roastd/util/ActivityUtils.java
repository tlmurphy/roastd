package net.jmhossler.roastd.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;

import static com.google.common.base.Preconditions.checkNotNull;

public class ActivityUtils {
  public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                            @NonNull Fragment fragment, int frameId) {
    checkNotNull(fragmentManager);
    checkNotNull(fragment);
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(frameId, fragment);
    transaction.commit();
  }

  public static boolean needToLogin(Context context) {
    return GoogleSignIn.getLastSignedInAccount(context) == null;
  }

  public static String getMapsDirectionsUrl(String origin, String destination) {
    // If we can get location services working, this will make it so that we can provide a direct
    //    link to directions. The other function also works, though.
    return "https://www.google.com/maps/dir/json?origin=" + origin
      + "&destination=" + destination;
  }

  public static String getMapsLocationUrl(String location) {
    return "https://www.google.com/maps/search/?api=1&query=" + location;
  }
}
