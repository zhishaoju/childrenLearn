package com.childrenLearn.utils;

import android.text.TextUtils;
import android.widget.Toast;
import com.childrenLearn.MainApplication;

/**
 * @Author zhi
 * @Date 2019/8/8 14:35
 * @Describer
 */
public class ToastUtil {

  public static void show(String msg) {
    if (!TextUtils.isEmpty(msg) && !("null".equals(msg))) {
      Toast.makeText(MainApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }
  }

}
