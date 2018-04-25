package net.jmhossler.roastd.beantask;

import net.jmhossler.roastd.data.bean.Bean;
import net.jmhossler.roastd.data.bean.BeanDataSource;
import net.jmhossler.roastd.data.review.ReviewDataSource;
import net.jmhossler.roastd.data.searchableItem.BaseDataSource;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.shop.ShopDataSource;

public class BeanPresenter implements BeanContract.Presenter {
  private final BeanDataSource mBeanDataSource;
  private final ShopDataSource mShopDataSource;
  private final ReviewDataSource mReviewDataSource;
  private BeanContract.View mView;
  private final String mBeanId;

  public BeanPresenter(BeanContract.View view, String beanId, BeanDataSource beanDataSource,
                       ShopDataSource shopDataSource, ReviewDataSource reviewDataSource) {
    mView = view;
    mBeanId = beanId;
    mBeanDataSource = beanDataSource;
    mShopDataSource = shopDataSource;
    mReviewDataSource = reviewDataSource;
    mView.setPresenter(this);
  }

  @Override
  public void start() {

  }

  @Override
  public void setShopInfo() {

  }

  @Override
  public void setName() {
    mBeanDataSource.get(mBeanId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        String name = item.getName();
        if (name == null) {
          name = "uh oh";
        }
        mView.displayName(name);
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setDescription() {
    mBeanDataSource.get(mBeanId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        String description = item.getDescription();
        if (description == null) {
          description = "something bad";
        }
        mView.displayDescription(description);
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setRoast() {
    mBeanDataSource.get(mBeanId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        Bean bean = (Bean) item;
        mView.displayRoast(bean.getRoastType());
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setOrigin() {
    mBeanDataSource.get(mBeanId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        Bean bean = (Bean) item;
        mView.displayOrigin(bean.getOrigin());
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  @Override
  public void setReview() {

  }

  @Override
  public void setImage() {

  }
}
