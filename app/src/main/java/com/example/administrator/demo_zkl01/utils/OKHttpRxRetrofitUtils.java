package com.example.administrator.demo_zkl01.utils;

import android.util.Log;

import com.example.administrator.demo_zkl01.item.ICallBack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author : 张自力
 * Created on time.
 *
 *
 * 网络请求工具类
 */

public class OKHttpRxRetrofitUtils {

    /**
     * 使用单例模式
     *
     */
    //1. 创建单例对象
    private static volatile OKHttpRxRetrofitUtils instance;
    private final Retrofit.Builder builder;

    //2. 单例构造方法
    private OKHttpRxRetrofitUtils() {
      //创建日志拦截器
        //1 级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //2 创建日志拦截器对象  并设置打印信息
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("okhttp日志拦截器信息打印:  ", "log: " + message);
            }
        });
        //为日志拦截器设置日志级别
        httpLoggingInterceptor.setLevel(level);

        //创建OKhttp对象  设置日志拦截器
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient().newBuilder();
        //ok对象  及其设置
        OkHttpClient okHttpClient = okhttpClientBuilder.connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        //创建Retrofit对
        builder = new Retrofit.Builder();
        builder.addConverterFactory(GsonConverterFactory.create())//添加解析工厂
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
               .client(okHttpClient);//添加日志拦截器

    }
    //3. 单例的双重锁机制
    public static OKHttpRxRetrofitUtils getInstance(){
        if(instance==null){
            synchronized (OKHttpRxRetrofitUtils.class){
                if(instance==null){
                    instance = new OKHttpRxRetrofitUtils();
                }
            }
        }
        return instance;
    }

    //4. 设置网络请求方法  从外部传入listener对象

    //4.1 doGet请求方法
    public OKHttpRxRetrofitUtils dogetU(String url,String keyurl, ICallBack iCallBack, RXRetrofitUtilsListener retrofitUtilsListener){
        //通过方法  得到Retrofit接口对象
        RetrofitView retrofitView = getRetrofitClass(url);
        //通过对象  调用方法
        retrofitView.doget(keyurl)
                .observeOn(AndroidSchedulers.mainThread())//接受线程
                .subscribeOn(Schedulers.io())//发送线程
                .subscribe(getObserver(retrofitUtilsListener));//注意这里的参数是下面创建的Observer对象
        //返回
       return instance;
    }

    //创建一个观察者  接收传过来的listener对象
    public Observer getObserver(final RXRetrofitUtilsListener retrofitUtilsListener){
        //这里注意 obsercer是rx包下的
        //并且Observer<ResponseBody> 是带尖括号的
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //发生错误的时候
                if(retrofitUtilsListener!=null){
                    retrofitUtilsListener.onFailderIU(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                //先接受数据
                try {
                    String result = responseBody.string();
                    //判断数据非空
                    if(!result.equals("")){
                      //判断对象是否为空
                        if(retrofitUtilsListener!=null){
                            //调用成功方法返回数据
                            retrofitUtilsListener.onSuccessIU(result);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    //发生错误的时候
                    if(retrofitUtilsListener!=null){
                        retrofitUtilsListener.onFailderIU(e.getMessage());
                    }
                }
            }
        };
        return observer;
    }



    //得到Retrofit接口对象
    private RetrofitView getRetrofitClass(String url) {
        Retrofit.Builder builderUrl = this.builder.baseUrl(url);
        Retrofit build = builderUrl.build();
        RetrofitView retrofitView = build.create(RetrofitView.class);
        return retrofitView;
    }


    //定义一个接口
    public interface RXRetrofitUtilsListener{
        //成功回调方法
        void onSuccessIU(String data);
        //失败回调方法
        void onFailderIU(String message);

    }

}
