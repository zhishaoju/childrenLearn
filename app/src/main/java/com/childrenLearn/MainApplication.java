package com.childrenLearn;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.widget.ImageView;
import com.childrenLearn.utils.LogUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import java.io.File;

/**
 * @Author zhi
 * @Date 2019/8/8 14:36
 * @Describer
 */
public class MainApplication extends Application {

  private final String TAG = MainApplication.class.getSimpleName();

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
