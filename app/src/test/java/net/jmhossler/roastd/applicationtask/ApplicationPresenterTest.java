package net.jmhossler.roastd.applicationtask;

import android.text.TextUtils;

import net.jmhossler.roastd.data.shopApplicationInfo.ShopApplicationInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TextUtils.class})
public class ApplicationPresenterTest {

  @Mock
  private ApplicationContract.View view;
  private ApplicationPresenter presenter;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    // For simulating TextUtils.isEmpty
    PowerMockito.mockStatic(TextUtils.class);
    PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer((Answer<Boolean>) invocation -> {
      CharSequence a = (CharSequence) invocation.getArguments()[0];
      return !(a != null && a.length() > 0);
    });
    presenter = new ApplicationPresenter(view);
  }

  @Test
  public void test_goodInput() {
    assert goodInput("Roastd", "1234567890", "a random address");
    assert !goodInput("", "1234567890", "a random address");
  }

  @Test
  public void test_postApplication() {
    presenter.postApplication();
    // Verify that the view finishes after a postApplication
    verify(view).finish();
  }

  private boolean goodInput(String shopName, String shopNum, String shopAddress) {
    when(view.getShopApplicationInfo()).thenReturn(
      new ShopApplicationInfo(shopName, shopNum, shopAddress));
    presenter.setShopInfo();
    return presenter.goodInput();
  }
}
