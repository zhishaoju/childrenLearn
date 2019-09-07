package com.childrenLearn;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static com.childrenLearn.utils.Contants.REQUEST_PERMISSION_CODE;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.childrenLearn.fragment.IndexFragment;
import com.childrenLearn.fragment.SecondFragment;
import com.childrenLearn.fragment.ThirdFragment;
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
import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

  private String TAG = MainActivity.class.getSimpleName();

  @BindView(R.id.tv_first)
  TextView tvFirst;

  @BindView(R.id.tv_second)
  TextView tvSecond;

  @BindView(R.id.tv_third)
  TextView tvThird;

  private Fragment mIndexFragment;
  private Fragment mSecondFragment;
  private Fragment mThirdFragment;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (!this.isTaskRoot()) {
      Intent intent = getIntent();
      if (intent != null) {
        String action = intent.getAction();
        if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
          finish();
          return;
        }
      }
    }
    initPermission();
    // 无title
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    // 全屏
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setTabSelect(0);

    initUniversalImageLoader();
    //这里将会设置所有播放器的皮肤 | Here the player will set all the skin
//        JCVideoPlayer.setGlobleSkin(R.color.colorPrimary, R.color.colorAccent, R.drawable.skin_seek_progress,
//                R.color.bottom_bg, R.drawable.skin_enlarge_video, R.drawable.skin_shrink_video);
    //这里将会改变所有缩略图的ScaleType | Here will change all thumbnails ScaleType
    JCVideoPlayer.setThumbImageViewScalType(ImageView.ScaleType.FIT_XY);
  }

  @OnClick({R.id.tv_first, R.id.tv_second, R.id.tv_third})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.tv_first:
        setTabSelect(0);
        break;
      case R.id.tv_second:
        setTabSelect(1);
        break;
      case R.id.tv_third:
        setTabSelect(2);
        break;
    }
  }

  private void setTabSelect(int index) {
    reset();
    FragmentManager manager = getSupportFragmentManager();

    FragmentTransaction transaction = manager.beginTransaction();

    hide(transaction);

    switch (index) {
      case 0:
        mIndexFragment = manager.findFragmentByTag("tag1");
        if (mIndexFragment == null) {
          mIndexFragment = new IndexFragment();
          transaction.add(R.id.fragment_main, mIndexFragment, "tag1");
        } else {
          transaction.show(mIndexFragment);
        }

        Drawable top1 = getResources().getDrawable(R.mipmap.index);
        tvFirst.setCompoundDrawablesWithIntrinsicBounds(null, top1, null, null);
        tvFirst.setTextColor(getResources().getColor(R.color.main_color));
        break;
      case 1:
        mSecondFragment = manager.findFragmentByTag("tag2");

        if (mSecondFragment == null) {
          mSecondFragment = new SecondFragment();
          transaction.add(R.id.fragment_main, mSecondFragment, "tag2");
        } else {
          transaction.show(mSecondFragment);
        }
        Drawable top2 = getResources().getDrawable(R.mipmap.dask);
        tvSecond.setCompoundDrawablesWithIntrinsicBounds(null, top2, null, null);
        tvSecond.setTextColor(getResources().getColor(R.color.main_color));
        break;
      case 2:
        mThirdFragment = manager.findFragmentByTag("tag3");
        if (mThirdFragment == null) {
          mThirdFragment = new ThirdFragment();
          transaction.add(R.id.fragment_main, mThirdFragment, "tag3");
        } else {
          transaction.show(mThirdFragment);
        }
        Drawable top3 = getResources().getDrawable(R.mipmap.my2);
        tvThird.setCompoundDrawablesWithIntrinsicBounds(null, top3, null, null);
        tvThird.setTextColor(getResources().getColor(R.color.main_color));
        break;

      default:
        break;
    }
    transaction.commitAllowingStateLoss();
  }

  private void reset() {
    Drawable top1 = getResources().getDrawable(R.mipmap.index2);
    tvFirst.setCompoundDrawablesWithIntrinsicBounds(null, top1, null, null);
    tvFirst.setTextColor(getResources().getColor(R.color.main_color2));

    Drawable top2 = getResources().getDrawable(R.mipmap.dask2);
    tvSecond.setCompoundDrawablesWithIntrinsicBounds(null, top2, null, null);
    tvSecond.setTextColor(getResources().getColor(R.color.main_color2));

    Drawable top3 = getResources().getDrawable(R.mipmap.my);
    tvThird.setCompoundDrawablesWithIntrinsicBounds(null, top3, null, null);
    tvThird.setTextColor(getResources().getColor(R.color.main_color2));
  }

  private void hide(FragmentTransaction tran) {

    if (mIndexFragment != null) {
      tran.hide(mIndexFragment);
    }

    if (mSecondFragment != null) {
      tran.hide(mSecondFragment);
    }

    if (mThirdFragment != null) {
      tran.hide(mThirdFragment);
    }
  }

  private void initPermission() {
    String permissions[] = {
        Manifest.permission.INTERNET,
        ACCESS_NETWORK_STATE,
        Manifest.permission.WRITE_SETTINGS,
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.CHANGE_WIFI_STATE,
        Manifest.permission.SYSTEM_ALERT_WINDOW,
        Manifest.permission.CAMERA,
        Manifest.permission.MODIFY_AUDIO_SETTINGS,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WAKE_LOCK,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.KILL_BACKGROUND_PROCESSES,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    ArrayList<String> toApplyList = new ArrayList<String>();

    for (String perm : permissions) {
      if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
        toApplyList.add(perm);
        // 进入到这里代表没有权限.
      }
    }
    String tmpList[] = new String[toApplyList.size()];
    if (!toApplyList.isEmpty()) {
      ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
    }
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, String[] permissions, int[] grantResults) {
    // 此处为android 6.0以上动态授权的回调，用户自行实现。
    LogUtil.showDLog(TAG, "onRequestPermissionsResult");
    switch (requestCode) {
      case REQUEST_PERMISSION_CODE: {
        boolean allPermissionGranted = true;
        for (int i = 0; i < grantResults.length; i++) {
          if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
            allPermissionGranted = false;
            LogUtil.showELog(TAG, "permission_denied");
          }
        }
        if (!allPermissionGranted) {
          Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
          intent.setData(Uri.parse("package:" + getPackageName()));
          startActivity(intent);
        }
      }
      break;
      default:
        break;
    }
  }

  private void initUniversalImageLoader() {

    LogUtil.showDLog(TAG, "initUniversalImageLoader()");
    DisplayImageOptions options = new DisplayImageOptions.Builder()
        .showImageOnLoading(new ColorDrawable(Color.parseColor("#f0f0f0")))
        .resetViewBeforeLoading(true)
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .considerExifParams(true)
        .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
        .bitmapConfig(Bitmap.Config.RGB_565)
        .build();

    int memClass = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE))
        .getMemoryClass();
    int memCacheSize = 1024 * 1024 * memClass / 8;

    File cacheDir = new File(
        Environment.getExternalStorageDirectory().getPath() + "/jiecao/cache");
    if (!cacheDir.exists()) {
      cacheDir.mkdirs();
    }

    LogUtil.showDLog(TAG, "cacheDir.exists() = " + cacheDir.exists());

    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .threadPoolSize(3)
        .threadPriority(Thread.NORM_PRIORITY - 2)
        .denyCacheImageMultipleSizesInMemory()
        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
        .memoryCache(new UsingFreqLimitedMemoryCache(memCacheSize))
        .memoryCacheSize(memCacheSize)
        .diskCacheSize(50 * 1024 * 1024)
        .tasksProcessingOrder(QueueProcessingType.LIFO)
        .diskCache(new UnlimitedDiskCache(cacheDir))
        .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000))
        .defaultDisplayImageOptions(options)
        .build();
    ImageLoader.getInstance().init(config);
  }
}
