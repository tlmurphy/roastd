package net.jmhossler.roastd.shoptask;

import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import net.jmhossler.roastd.data.review.Review;
import net.jmhossler.roastd.data.review.ReviewDataSource;
import net.jmhossler.roastd.data.searchableItem.BaseDataSource;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.searchableItem.SearchableItemDataSource;
import net.jmhossler.roastd.data.shop.Shop;
import net.jmhossler.roastd.data.shop.ShopDataSource;
import net.jmhossler.roastd.data.user.User;
import net.jmhossler.roastd.data.user.UserDataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static android.support.constraint.Constraints.TAG;

public class ShopPresenter implements ShopContract.Presenter {
  private final String mShopId;
  private final UserDataSource mUserDataSource;
  private final ShopContract.View mView;
  private final ShopDataSource mShopDataSource;
  private final SearchableItemDataSource mSearchableItemDataSource;
  private final ReviewDataSource mReviewDataSource;
  private User mUser;
  private FirebaseAuth mAuth;


  public ShopPresenter(ShopContract.View view, String shopId, UserDataSource userDataSource, ShopDataSource shopDataSource,
                       SearchableItemDataSource searchableItemDataSource, ReviewDataSource reviewDataSource,
                       FirebaseAuth firebaseAuth) {
    mShopId = shopId;
    mUserDataSource = userDataSource;
    mView = view;
    mShopDataSource = shopDataSource;
    mSearchableItemDataSource = searchableItemDataSource;
    mReviewDataSource = reviewDataSource;
    mAuth = firebaseAuth;
    mView.setPresenter(this);
  }

  @Override
  public void start() {
    mUserDataSource.getUser(mAuth.getUid(), new UserDataSource.GetUserCallback() {
      @Override
      public void onUserLoaded(User user) {
        if (user == null) {
          Log.d(TAG, "User " + mAuth.getUid() + " does not exist! This is bad!");
          mView.finish();
        }

        mUser = user;

        mShopDataSource.get(mShopId, new BaseDataSource.GetCallback() {
          @Override
          public void onLoaded(SearchableItem item) {
            setAddress(item);
            setName(item);
            setDescription(item);
            setMapsUrl(item);
            setCurrentRating(item);
            setImage(item);
          }
          @Override
          public void onDataNotAvailable() { }
        });
      }

      @Override
      public void onDataNotAvailable() { Log.d(TAG, "Error with Firebase Instance"); }
    });
  }

  @Override
  public void setConsumeables() {
    mShopDataSource.get(mShopId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        Shop shop = (Shop) item;
        Map<String, Boolean> consumeables = shop.getItemUUIDs();
        if (consumeables == null) {
          consumeables = new HashMap<>();
        }
        mSearchableItemDataSource.getSearchableItems(new ArrayList<>(consumeables.keySet()), new SearchableItemDataSource.LoadSearchableItemsCallback() {
          @Override
          public void onSearchableItemsLoaded(List<SearchableItem> items) {
            mView.displayConsumeables(items);
          }

          @Override
          public void onDataNotAvailable() {

          }
        });
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setName(SearchableItem item) {
    String name = item.getName();
    if(name == null) {
      name = "Uh Oh";
    }
    mView.displayName(name);
  }

  @Override
  public void setImage(SearchableItem item) {
    String imageUrl = item.getImage();
    if (imageUrl != null) {
      mView.displayImage(imageUrl);
    }
  }

  @Override
  public void setDescription(SearchableItem item) {
    String description = item.getDescription();
    if(description == null) {
      description = "Uh Oh";
    }
    mView.displayDescription(description);
  }

  @Override
  public void setAddress(SearchableItem item) {
    Shop shop = (Shop) item;
    String address = shop.getAddress();
    if(address == null) {
      address = "Uh Oh";
    }
    mView.displayAddress(address);
  }

  @Override
  public void setMapsUrl(SearchableItem item) {
    Shop shop = (Shop) item;
    String address = shop.getAddress();

    mView.createMapsLink(address);
  }

  @Override
  public void setCurrentRating(SearchableItem item) {
    mReviewDataSource.getReviews(new ReviewDataSource.LoadReviewsCallback() {
      @Override
      public void onReviewsLoaded(List<Review> reviews) {
        Boolean reviewFound = false;
        for (Review r : reviews) {
          if (item.getReviewIds().get(r.getUuid()) != null && r.getUserUuid().equals(mUser.getUuid())) {
            reviewFound = true;
            mView.displayRating(r.getScore());
          }
        }
        // User hasn't reviewed this item yet
        if (!reviewFound) {
          // Set the empty rating
          mView.displayRating(0);
        }
      }
      @Override
      public void onDataNotAvailable() { Log.d(TAG, "No reviews found!"); }
    });
  }

  @Override
  public void setNewRating(float rating) {
    mUserDataSource.getUser(mAuth.getUid(), new UserDataSource.GetUserCallback() {
      @Override
      public void onUserLoaded(User user) {
        if (user == null) {
          Log.d(TAG, "User " + mAuth.getUid() + " does not exist! This is bad!");
          mView.finish();
        }

        mUser = user;

        mShopDataSource.get(mShopId, new BaseDataSource.GetCallback() {
          @Override
          public void onLoaded(SearchableItem item) {
            // Try to check if the user has already reviewed this item
            mReviewDataSource.getReviews(new ReviewDataSource.LoadReviewsCallback() {
              @Override
              public void onReviewsLoaded(List<Review> reviews) {
                Boolean reviewFound = false;
                for (Review r : reviews) {
                  if (item.getReviewIds().get(r.getUuid()) != null && r.getUserUuid().equals(mUser.getUuid())) {
                    reviewFound = true;
                    r.setScore((int) rating);
                    mReviewDataSource.saveReview(r);
                  }
                }
                // User hasn't reviewed this item yet
                if (!reviewFound) {
                  // Create a review and save it
                  String reviewID = UUID.randomUUID().toString();
                  mReviewDataSource.saveReview(new Review(reviewID, mUser.getUuid(), (int) rating));
                  item.addReviewId(reviewID);
                  mShopDataSource.saveShop((Shop) item);
                }
              }
              @Override
              public void onDataNotAvailable() { Log.d(TAG, "No reviews found!"); }
            });
          }
          @Override
          public void onDataNotAvailable() { Log.d(TAG, "Could not find shop of UUID: " + mShopId); }
        });
      }
      @Override
      public void onDataNotAvailable() { Log.d(TAG, "Error with Firebase Instance"); }
    });
  }
}
