package com.example.administrator.demo_zkl01.utils;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Author : 张自力
 * Created on time.
 *
 * Retrofit 接口
 */

public interface RetrofitView {

    //1 doGet方法
    //http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=电脑&page=1&count=10
    @GET
    Observable<ResponseBody> doget(@Url String url);

}
