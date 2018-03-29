package net.jmhossler.roastd;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class User {

  public static void setUsername(Context context, String username) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString("username", username);
    editor.apply();
  }

  public static String getUsername(Context context) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getString("username", "Unknown");
  }

  public static void setPhotoUrl(Context context, String url) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString("photoUrl", url);
    editor.apply();
  }

  public static String getPhotoUrl(Context context) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getString("photoUrl", "");
  }

  public static void setEmail(Context context, String email) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString("email", email);
    editor.apply();
  }

  public static String getEmail(Context context) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getString("email", "");
  }
}
