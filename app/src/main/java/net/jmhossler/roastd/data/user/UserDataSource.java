package net.jmhossler.roastd.data.user;

public interface UserDataSource {

  interface GetUserCallback {

    void onUserLoaded(User user);

    void onDataNotAvailable();
  }

  void getUser(String userId, GetUserCallback callback);

  void saveUser(User user);
}
