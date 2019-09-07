package com.childrenLearn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.childrenLearn.MainActivity;
import com.childrenLearn.R;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * @Author zhisiyi
 * @Date 2019.09.05 14:53
 * @Comment
 */
public class ShowVideoActivity extends FragmentActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_vedio);

    JCVideoPlayer videoController1 = (JCVideoPlayer) findViewById(R.id.videocontroller1);
    videoController1
        .setUp("http://2449.vod.myqcloud.com/2449_43b6f696980311e59ed467f22794e792.f20.mp4",
            "http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640",
            "一行代码实现视频播放");

//    JCVideoPlayer.toFullscreenActivity(this,
//        "http://2449.vod.myqcloud.com/2449_43b6f696980311e59ed467f22794e792.f20.mp4",
//        "http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640",
//        "一行代码实现视频播放");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    JCVideoPlayer.releaseAllVideos();
  }
}
