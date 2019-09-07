package com.childrenLearn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.childrenLearn.R;
import com.childrenLearn.activity.ShowVideoActivity;
import com.childrenLearn.utils.LogUtil;

public class IndexFragment extends BaseFragment {

  private static final String TAG = IndexFragment.class.getSimpleName();

  private Unbinder mUnbinder;

  @BindView(R.id.bt_yazi)
  Button btQiehuan;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_index, container, false);
    view.findViewById(R.id.bt_yazi).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(getActivity(), ShowVideoActivity.class));
      }
    });
    mUnbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  private void initValue() {
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      // 请求网络
      refresh();
    }
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    LogUtil.showELog(TAG, "hidden = " + hidden);
    if (!hidden) {
      // 请求网络展示界面
      refresh();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    LogUtil.showELog(TAG, "onResume");
    // 第一次进来的时候，会走到这里而不走onHiddenChanged
    // 请求网络展示界面
    refresh();
  }

  private void refresh() {
    LogUtil.showDLog(TAG, "refresh()");
  }

  private void loadMore(int pageNum) {
  }

  private void initListener() {
  }

  private void parseData(String data) {
    LogUtil.showELog(TAG, "parseData(String data) 解析数据data：" + data);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mUnbinder.unbind();
  }

//  @OnClick(R.id.bt_yazi)
//  public void onViewClicked(View view) {
//    switch (view.getId()) {
//      case R.id.bt_yazi:
//        startActivity(new Intent(getActivity(), ShowVideoActivity.class));
//        break;
//      default:
//        break;
//    }
//  }
}
