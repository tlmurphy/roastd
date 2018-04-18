package net.jmhossler.roastd.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

// Description: Data object for a user. Contains unique user id, email, name, photoURL,
//    shopIds, isAdmin, createdOn, and favoriteUuids
public class User {

  @NonNull
  public String uuid;

  @NonNull
  public String email;

  @NonNull
  public String name;

  @Nullable
  public String photoURL;

  // This is the list of shop ids that the user managers. Could it make sense
  //    to just have this as the distinguishing factor between managers and regular users?
  //    If it is this way, we keep the user in the same place, just add shopIds to their
  //    shopIds list in the database and bam, their a manager.
  @NonNull
  public String[] shopIds;

  // ?? I thought maybe if we remove the manager class, we could remove the admin
  //    class as well. idk, let me know what you guys think
  @NonNull
  public boolean isAdmin;

  @NonNull
  public Date createdOn;

  // I am completely happy with renaming this. These uuids are the uuids of the SearchableItems that
  //    the user has favorited. This way, we have access to that information.
  @NonNull
  public String[] favoriteUuids;

  public User() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public User(@NonNull String uuid, @NonNull String email,
              @NonNull String name, @Nullable String photoURL,
              @NonNull String[] shopIds, @NonNull boolean isAdmin,
              @NonNull Date createdOn, @NonNull String[] favoriteUuids) {
    this.uuid = uuid;
    this.email = email;
    this.name = name;
    this.photoURL = photoURL;
    this.isAdmin = isAdmin;
    this.shopIds = shopIds;
    this.createdOn = createdOn;
    this.favoriteUuids = favoriteUuids;
  }
}
