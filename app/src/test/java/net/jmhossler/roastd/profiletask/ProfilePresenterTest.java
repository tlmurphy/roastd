package net.jmhossler.roastd.profiletask;


import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for the implementation of {@link ProfilePresenter}
 */
public class ProfilePresenterTest {

  @Mock
  private ProfileContract.View mProfileView;

  private ProfilePresenter mProfilePresenter;

  @Mock
  private FirebaseAuth mAuth;

  @Mock
  private GoogleSignInClient mGoogleSignInClient;

  @Before
  public void setupTasksPresenter() {
    MockitoAnnotations.initMocks(this);

    mProfilePresenter = new ProfilePresenter(mProfileView, mAuth, mGoogleSignInClient);
  }

  /**
   *   need tests here. Can't figure out how to instantiate instances of mAuth and
   *   mGoogleSignInClient in order to properly test
   */
}
