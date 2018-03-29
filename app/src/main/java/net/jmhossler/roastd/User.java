package net.jmhossler.roastd;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class User {

  private static SharedPreferences.Editor getEditor(Context context) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.edit();
  }

  public static void setUsername(Context context, String username) {
    SharedPreferences.Editor e = getEditor(context);
    e.putString("username", username);
    e.apply();
  }

  public static String getUsername(Context context) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getString("username", "Unknown");
  }

  public static void setPhotoUrl(Context context, String url) {
    SharedPreferences.Editor e = getEditor(context);
    e.putString("photoUrl", url);
    e.apply();
  }

  public static String getPhotoUrl(Context context) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getString("photoUrl", "");
  }

  public static void setEmail(Context context, String email) {
    SharedPreferences.Editor e = getEditor(context);
    e.putString("email", email);
    e.apply();
  }

  public static String getEmail(Context context) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getString("email", "");
  }
}
