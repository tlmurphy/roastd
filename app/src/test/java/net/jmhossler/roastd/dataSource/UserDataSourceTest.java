package net.jmhossler.roastd.dataSource;

import net.jmhossler.roastd.data.user.MockUserRepository;
import net.jmhossler.roastd.data.user.User;
import net.jmhossler.roastd.data.user.UserDataSource;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.Is.is;

public class UserDataSourceTest {

  private UserDataSource mDataSource;
  private User mUser;
  private boolean mFailed;

  @Before
  public void resetSingletonAndUser() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Field f = MockUserRepository.class.getDeclaredField("sInstance");
    f.setAccessible(true);
    f.set(null, null);

    mDataSource = MockUserRepository.getInstance();
    mUser = null;
    mFailed = false;
  }

  @Test
  public void userDataSource_canGetInstance() {
    assertThat(mDataSource, is(notNullValue()));
  }

  @Test
  public void userDataSource_cannotGetUserThatDoesNotExist() {

    mUser = constructEmptyUser();
    assertThat(mUser, is(notNullValue()));

    mDataSource.getUser("", new UserDataSource.GetUserCallback() {
      @Override
      public void onUserLoaded(User user) {
        mUser = user;
      }

      @Override
      public void onDataNotAvailable() {
        mUser = null;
      }
    });

    assertThat(mUser, is(nullValue()));
  }

  @Test
  public void userDataSource_canSaveAndGetUser() {
    User u = constructEmptyUser();
    u.setName("Bob Ross");

    mDataSource.saveUser(u);

    mDataSource.getUser(u.getUuid(), new UserDataSource.GetUserCallback() {
      @Override
      public void onUserLoaded(User user) {
        mUser = user;
      }

      @Override
      public void onDataNotAvailable() {
        mFailed = true;
      }
    });

    boolean r = u.equals(mUser);

    assertThat(mFailed, is(false));
    assertThat(mUser, not(sameInstance(u)));
    assertThat(mUser, is(equalTo(u)));
  }

  private User constructEmptyUser() {
    return new User("001", "", "", "", new ArrayList<>(),
      false, new Date(), new ArrayList<>());
  }
}
