package net.jmhossler.roastd.data.user;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// Description: Data object for a user. Contains unique user id, email, name, photoURL,
//    shopIds, isAdmin, createdOn, and favoriteUuids
public class User implements Serializable{

  @NonNull
  private String uuid;

  @NonNull
  private String email;

  @NonNull
  private String name;

  @Nullable
  private String photoURL;

  // This is the list of shop ids that the user managers. Could it make sense
  //    to just have this as the distinguishing factor between managers and regular users?
  //    If it is this way, we keep the user in the same place, just add shopIds to their
  //    shopIds list in the database and bam, their a manager.
  @NonNull
  private List<UUID> shopIds;

  // ?? I thought maybe if we remove the manager class, we could remove the admin
  //    class as well. idk, let me know what you guys think
  @NonNull
  private boolean isAdmin;

  @NonNull
  private Date createdOn;

  // I am completely happy with renaming this. These uuids are the uuids of the SearchableItems that
  //    the user has favorited. This way, we have access to that information.
  @NonNull
  private List<UUID> favoriteUuids;

  public User() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public User(@NonNull String uuid, @NonNull String email,
              @NonNull String name, @Nullable String photoURL,
              @NonNull List<UUID> shopIds, @NonNull boolean isAdmin,
              @NonNull Date createdOn, @NonNull List<UUID> favoriteUuids) {
    this.setUuid(uuid);
    this.setEmail(email);
    this.setName(name);
    this.setPhotoURL(photoURL);
    this.setAdmin(isAdmin);
    this.setShopIds(shopIds);
    this.setCreatedOn(createdOn);
    this.setFavoriteUuids(favoriteUuids);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj == null) {
      return false;
    } else if (obj instanceof User) {
      if (getUuid().equals(((User) obj).getUuid())) {
        return true;
      }
    }
    return false;
  }

  @NonNull
  public String getUuid() {
    return uuid;
  }

  public void setUuid(@NonNull String uuid) {
    this.uuid = uuid;
  }

  @NonNull
  public String getEmail() {
    return email;
  }

  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  @Nullable
  public String getPhotoURL() {
    return photoURL;
  }

  public void setPhotoURL(@Nullable String photoURL) {
    this.photoURL = photoURL;
  }

  @NonNull
  public List<UUID> getShopIds() {
    return shopIds;
  }

  public void setShopIds(@NonNull List<UUID> shopIds) {
    this.shopIds = shopIds;
  }

  @NonNull
  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(@NonNull boolean admin) {
    isAdmin = admin;
  }

  @NonNull
  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(@NonNull Date createdOn) {
    this.createdOn = createdOn;
  }

  @NonNull
  public List<UUID> getFavoriteUuids() {
    return favoriteUuids;
  }

  public void setFavoriteUuids(@NonNull List<UUID> favoriteUuids) {
    this.favoriteUuids = favoriteUuids;
  }
}
