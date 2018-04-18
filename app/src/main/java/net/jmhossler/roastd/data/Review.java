package net.jmhossler.roastd.data;

import android.support.annotation.NonNull;

import com.google.common.primitives.UnsignedInteger;

// Description: Object to contain an individual review by a user for a SearchableItem. This
//    connects the rating, the user, and the SearchableItem. Each SearchableItem has a reference
//    to the reviews id
public class Review {
  @NonNull
  public String uuid;

  @NonNull
  public String userUuid;

  // It could be useful to shift this to an enum, but I'm not sure how we would do that in
  //    the database as well, so I am putting it as an unsigned integer for now. I am interested
  //    how firebase handles non-standard types.
  @NonNull
  public UnsignedInteger score;

  public Review() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public Review(@NonNull String uuid, @NonNull String userUuid,
                @NonNull UnsignedInteger score) {
    this.uuid = uuid;
    this.userUuid = userUuid;
    this.score = score;
  }
}
