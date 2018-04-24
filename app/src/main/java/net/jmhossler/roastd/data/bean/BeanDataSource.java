package net.jmhossler.roastd.data.bean;

import net.jmhossler.roastd.data.searchableItem.BaseDataSource;

import java.util.Map;

public interface BeanDataSource extends BaseDataSource {

  interface LoadBeansCallback {

    void onBeansLoaded(Map<String, Bean> beans);

    void onDataNotAvailable();
  }

  void getBeans(LoadBeansCallback callback);

  void saveBean(Bean bean);
}

