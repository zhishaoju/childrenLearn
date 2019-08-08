package com.childrenLearn;

import android.Manifest;
import android.Manifest.permission;
import android.app.Application;

/**
 * @Author zhi
 * @Date 2019/8/8 14:36
 * @Describer
 */
public class MainApplication extends Application {
  public static MainApplication mInstance;

  public static MainApplication getInstance() {
    return mInstance;
  }


  @Override
  public void onCreate() {
    super.onCreate();
    mInstance = this;

  }
}
