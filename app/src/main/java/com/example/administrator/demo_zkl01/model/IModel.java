package com.example.administrator.demo_zkl01.model;


import com.example.administrator.demo_zkl01.item.ICallBack;

import java.lang.reflect.Type;

/**
 * Author : 张自力
 * Created on time.
 *
 * 数据网址请求接口
 */

public interface IModel {

    //doget方法用户搜索
    void doGetIM(String url,String keyurl, ICallBack iCallBack, Type type);

    //doget 用户点击商品  展示详情
    void doGetShopXQIM(String url,int commoddityId, ICallBack iCallBack, Type type);

}
