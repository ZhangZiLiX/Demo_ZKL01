package com.example.administrator.demo_zkl01.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.administrator.demo_zkl01.dao.DaoMaster;
import com.example.administrator.demo_zkl01.dao.DaoSession;

/**
 * Author : 张自力
 * Created on time.
 *
 * 全局配置
 *
 */

public class MyApplication extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        //全局网络判断
        initNetWork(this);
        //GreenDao配置
        initGreenDao();
    }

    //GreenDao配置
    public void initGreenDao() {
        //通过DaoMaster得到Helper对象
        DaoMaster.DevOpenHelper shoppingHelper = new DaoMaster.DevOpenHelper(this, "shopping_db");
        //为helper设置
        SQLiteDatabase writableDatabase = shoppingHelper.getWritableDatabase();
        //创建DaoMaser 设置参数
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }

    //全局网络判断
    public static boolean initNetWork(Context context) {
        boolean available = false;

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if(networkInfo!=null){
            available = networkInfo.isAvailable();
        }
        return available;
    }

}
