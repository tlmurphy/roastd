/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.jmhossler.roastd.logintask;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of {@link LoginPresenter}
 */
public class LoginPresenterTest {

  @Mock
  private LoginContract.View mLoginView;

  private LoginPresenter mLoginPresenter;

  @Before
  public void setupTasksPresenter() {
    // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
    // inject the mocks in the test the initMocks method needs to be called.
    MockitoAnnotations.initMocks(this);

    // Get a reference to the class under test
    mLoginPresenter = new LoginPresenter(mLoginView);
  }

  @Test
  public void createPresenter_setsThePresenterToView() {
    // Get a reference to the class under test
    mLoginPresenter = new LoginPresenter(mLoginView);

    // Then the presenter is set to the view
    verify(mLoginView).setPresenter(mLoginPresenter);
  }

  @Test
  public void signInClicked() {
    // Verify that, when the sign in is clicked, the sign in process is started
    mLoginPresenter.signInClicked();

    verify(mLoginView).startGoogleSignin();
  }
}
