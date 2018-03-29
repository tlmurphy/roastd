package net.jmhossler.roastd;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class User {

  private static SharedPreferences.Editor getEditor(Context context) {
   return PreferenceManager.getDefaultSharedPreferences(context).edit();
  }

static void setUsername(Context context, String username) {
    getEditor(context).putString("username", username).apply();
  }

static String getUsername(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context).getString("username", "Unknown");
  }

static void setPhotoUrl(Context context, String url) {
   getEditor(context).putString("photoUrl", url).apply();
  }

static String getPhotoUrl(Context context) {
   return PreferenceManager.getDefaultSharedPreferences(context).getString("photoUrl", "");
  }

static void setEmail(Context context, String email) {
    getEditor(context).putString("email", email).apply();
  }

static String getEmail(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context).getString("email", "");
  }
}
