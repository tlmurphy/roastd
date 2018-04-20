package net.jmhossler.roastd.data.bean;

import android.util.Base64;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static net.jmhossler.roastd.data.MockDataSourceUtils.deepClone;

public class MockBeanRepository implements BeanDataSource {

  private static MockBeanRepository sInstance = null;
  private static ArrayList<Bean> sBeans;

  private MockBeanRepository() {

  }

  public static MockBeanRepository getInstance() {
    if (sInstance == null) {
      sInstance = new MockBeanRepository();
      sBeans = new ArrayList<>();
      sBeans.add(new Bean(UUID.fromString("1-1-1-1-1"), "Colombian",
        "Colombian coffee was first introduced to the country of Colombia in the early 1800s. " +
          "Today Maragogipe, Caturra, Typica and Bourbon cultivars are grown. When Colombian " +
          "coffee is freshly roasted it has a bright acidity, is heavy in body and is intensely aromatic.",
        Base64.encode("hello world".getBytes(), Base64.DEFAULT), new ArrayList<>(), "medium", "Colombia"));
    }
    return sInstance;
  }

  @Override
  public void getBean(UUID beanId, GetBeanCallback callback) {
    Optional<Bean> op = sBeans.stream()
      .filter(bean -> bean.getUuid() == beanId)
      .findFirst();

    if (op.isPresent()) {
      // Using cloning to try to emulate repository behavior
      callback.onBeanLoaded(deepClone(op.get()));
    } else {
      callback.onDataNotAvailable();
    }
  }

  @Override
  public void getBeans(LoadBeansCallback callback) {
    // Using cloning to try to emulate repository behavior
    callback.onBeansLoaded(deepClone(sBeans));
  }

  @Override
  public void saveBean(Bean bean) {
    int index =
      IntStream.range(0, sBeans.size())
      .filter(i -> sBeans.get(i).getUuid() == bean.getUuid())
      .findFirst()
      .orElse(-1);

    // Using cloning to try to emulate repository behavior
    Bean copy = deepClone(bean);

    if (index == -1) {
      sBeans.add(copy);
    } else {
      sBeans.set(index, copy);
    }
  }
}
