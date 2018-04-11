package net.jmhossler.roastd.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;

// TODO: This file was made irrelevant for what we've done so far. I'm leaving it here as an example
// of how the structure of these data classes will look.

@Entity(tableName = "users")
public final class User {

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "email")
  private final String mEmail;

  @NonNull
  @ColumnInfo(name = "name")
  private final String mName;

  @Nullable
  @ColumnInfo(name = "photoURL")
  private final String mPhotoURL;

  public User(@NonNull String email, @NonNull String name, @Nullable String photoURL) {
    mEmail = email;
    mName = name;
    mPhotoURL = photoURL;
  }

  @NonNull
  public String getEmail() { return mEmail; }

  @NonNull
  public String getName() { return mName; }

  @Nullable
  public String getPhotoURL() { return mPhotoURL; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equal(mEmail, user.getEmail());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mEmail, mName, mPhotoURL);
  }

  @Override
  public String toString() {
    return "User with email " + mEmail;
  }
}
