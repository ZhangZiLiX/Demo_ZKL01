package com.example.administrator.demo_zkl01.model;

import com.example.administrator.demo_zkl01.item.ICallBack;
import com.example.administrator.demo_zkl01.utils.OKHttpRxRetrofitUtils;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Author : 张自力
 * Created on time.
 *
 *
 * 数据请求层
 */

public class Model implements IModel {

    //doget 用户搜索
    @Override
    public void doGetIM(String url, String key, final ICallBack iCallBack, final Type type) {
        //注意：这里使用的接口回调对象是当做参数传递过去的RxRetrofitUtils.xRetrofitUtilsListener
        //调用网络请求工具类方法
        String keyuel = "small/commodity/v1/findCommodityByKeyword?page=1&count=10&keyword="+key;
        OKHttpRxRetrofitUtils.getInstance().dogetU(url,keyuel,iCallBack, new OKHttpRxRetrofitUtils.RXRetrofitUtilsListener() {
            @Override
            public void onSuccessIU(String data) {
                //使用Gson解析数据
                Gson gson = new Gson();
                Object o = gson.fromJson(data, type);
                if(iCallBack!=null){
                    //如果对象不为空  就将数据传回
                    iCallBack.onICSuccess(o);
                }
            }

            @Override
            public void onFailderIU(String message) {
                if(iCallBack!=null){
                    //如果对象不为空  就将数据传回
                    iCallBack.onICFailder(message);
                }
            }
        });
    }

    //doget 用户点击商品  展示详情
    @Override
    public void doGetShopXQIM(String url, int commodityId, final ICallBack iCallBack, final Type type) {
        //注意：这里使用的接口回调对象是当做参数传递过去的RxRetrofitUtils.xRetrofitUtilsListener
        //调用网络请求工具类方法
        //http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=6
        String keyuel = "small/commodity/v1/findCommodityDetailsById?commodityId="+commodityId;
        OKHttpRxRetrofitUtils.getInstance().dogetU(url,keyuel,iCallBack, new OKHttpRxRetrofitUtils.RXRetrofitUtilsListener() {
            @Override
            public void onSuccessIU(String data) {
                //使用Gson解析数据
                Gson gson = new Gson();
                Object o = gson.fromJson(data, type);
                if(iCallBack!=null){
                    //如果对象不为空  就将数据传回
                    iCallBack.onICSuccess(o);
                }
            }

            @Override
            public void onFailderIU(String message) {
                if(iCallBack!=null){
                    //如果对象不为空  就将数据传回
                    iCallBack.onICFailder(message);
                }
            }
        });
    }

}
