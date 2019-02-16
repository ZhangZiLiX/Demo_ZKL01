package com.example.administrator.demo_zkl01.presenter;

/**
 * Author : 张自力
 * Created on time.
 *
 *
 * 关联M  和 View的Presenter接口
 */

public interface IPresenter {

    //doGet方法  用户搜索
    void doGetIP(String key);

    //doGet方法 商品详情
    void doGetShopXQIP(int commodityId);


}
