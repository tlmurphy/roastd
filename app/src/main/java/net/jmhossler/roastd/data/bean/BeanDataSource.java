package net.jmhossler.roastd.data.bean;

import java.util.List;
import java.util.UUID;

public interface BeanDataSource {

  interface GetBeanCallback {

    void onBeanLoaded(Bean bean);

    void onDataNotAvailable();
  }

  interface LoadBeansCallback {

    void onBeansLoaded(List<Bean> beans);

    void onDataNotAvailable();
  }

  void getBean(UUID beanId, GetBeanCallback callback);

  void getBeans(LoadBeansCallback callback);

  void saveBean(Bean bean);
}

