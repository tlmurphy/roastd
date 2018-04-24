package net.jmhossler.roastd.data.review;

import android.support.annotation.NonNull;

// Description: Object to contain an individual review by a user for a SearchableItem. This
//    connects the rating, the user, and the SearchableItem. Each SearchableItem has a reference
//    to the reviews id
public class Review {
  @NonNull
  private String uuid;

  @NonNull
  private String userUuid;

  // It could be useful to shift this to an enum, but I'm not sure how we would do that in
  //    the database as well, so I am putting it as an integer.
  @NonNull
  private int score;

  public Review() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Review(@NonNull String uuid, @NonNull String userUuid,
                @NonNull int score) {
    this.setUuid(uuid);
    this.setUserUuid(userUuid);
    this.setScore(score);
  }

  @NonNull
  public String getUuid() {
    return uuid;
  }

  public void setUuid(@NonNull String uuid) {
    this.uuid = uuid;
  }

  @NonNull
  public String getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(@NonNull String userUuid) {
    this.userUuid = userUuid;
  }

  @NonNull
  public int getScore() {
    return score;
  }

  public void setScore(@NonNull int score) {
    if (score < 0) {
      throw new IllegalArgumentException("Cannot set a negative score value for a review. " +
        "Bad value was " + score);
    } else {
      this.score = score;
    }
  }
}
