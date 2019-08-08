package com.childrenLearn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.childrenLearn.R;
import com.childrenLearn.utils.LogUtil;

public class ThirdFragment extends BaseFragment {

  private final String TAG = ThirdFragment.class.getSimpleName();

  private Unbinder mUnbinder;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_my, container, false);
    mUnbinder = ButterKnife.bind(this, view);
    initValue();
    initListener();
    return view;
  }

  private void initListener() {}

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  private void initValue() {}

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    LogUtil.showELog(TAG, "hidden = " + hidden);
    if (!hidden) {
      // 请求网络展示界面
      netRequest();
    }
  }

  private void netRequest() {}

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mUnbinder.unbind();
  }
}
