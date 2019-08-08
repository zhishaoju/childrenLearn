package com.childrenLearn.Net;

/**
 * @Author zhi
 * @Date 2019/8/8 14:29
 * @Describer
 */
public interface NetInterface {
  interface RequestResponse {
    void failure(Exception e);

    void success(String data);
  }
}
