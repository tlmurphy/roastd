package net.jmhossler.roastd.data.bean;

import net.jmhossler.roastd.data.searchableItem.BaseDataSource;

import java.util.List;


public interface BeanDataSource extends BaseDataSource {

  interface LoadBeansCallback {

    void onBeansLoaded(List<Bean> beans);
    void onDataNotAvailable();
  }

  void getBeans(LoadBeansCallback callback);

  void saveBean(Bean bean);
}

