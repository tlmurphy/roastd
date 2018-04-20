package net.jmhossler.roastd.data.user;

import static net.jmhossler.roastd.data.MockDataSourceUtils.deepClone;

public class MockUserRepository implements UserDataSource {

  private static MockUserRepository sInstance = null;
  private static User sTheUser = null;

  private MockUserRepository() {

  }

  public static MockUserRepository getInstance() {
    if (sInstance == null) {
      sInstance = new MockUserRepository();
    }
    return sInstance;
  }

  @Override
  public void getUser(String userId, GetUserCallback callback) {
    if (sTheUser != null && userId.equals(sTheUser.getUuid())) {
      // Using cloning to try to emulate repository behavior
      callback.onUserLoaded(deepClone(sTheUser));
    } else {
      callback.onDataNotAvailable();
    }
  }

  @Override
  public void saveUser(User user) {
    // Using cloning to try to emulate repository behavior
    sTheUser = deepClone(user);
  }
}
