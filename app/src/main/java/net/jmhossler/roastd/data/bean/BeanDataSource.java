package net.jmhossler.roastd.data.bean;

import java.util.Map;

public interface BeanDataSource {

  interface GetBeanCallback {

    void onBeanLoaded(Bean bean);

    void onDataNotAvailable();
  }

  interface LoadBeansCallback {

    void onBeansLoaded(Map<String, Bean> beans);

    void onDataNotAvailable();
  }

  void getBean(String beanId, GetBeanCallback callback);

  void getBeans(LoadBeansCallback callback);

  void saveBean(Bean bean);
}

