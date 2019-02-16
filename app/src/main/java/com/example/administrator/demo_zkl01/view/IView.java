package com.example.administrator.demo_zkl01.view;

/**
 * Author : 张自力
 * Created on time.
 *
 * 接口
 */

public interface IView<T> {

    //成功失败的方法
    void onIVSuccess(T t);
    void onIVFailder(String str);

}
