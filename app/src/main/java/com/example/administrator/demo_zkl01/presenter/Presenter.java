package com.example.administrator.demo_zkl01.presenter;


import com.example.administrator.demo_zkl01.item.ICallBack;
import com.example.administrator.demo_zkl01.model.Model;
import com.example.administrator.demo_zkl01.startfragmentpage.homepage.bean.DetailsBean;
import com.example.administrator.demo_zkl01.startfragmentpage.homepage.bean.SearchBean;
import com.example.administrator.demo_zkl01.utils.API;
import com.example.administrator.demo_zkl01.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Author : 张自力
 * Created on time.
 *
 * 关联M  和 V 的Presenter层
 */

public class Presenter implements IPresenter {

    //初始化View 和 Model对象
    private IView iView;
    private Model model;

    //创建关联方法
    public void attach(IView mIView){
         iView= mIView;
         model= new Model();
    }


    //doGet方法
    @Override
    public void doGetIP(String key) {

        //定义一个泛型
        Type type = new TypeToken<SearchBean>(){}.getType();

        //使用Model对象  调用网络请求方法
        model.doGetIM(API.APINewsUrl,key,new ICallBack() {
            @Override
            public void onICSuccess(Object o) {
                //需要将数据强转  得到封装对象
                //SearchBean searchBean = (SearchBean) o;
                /*if(!searchBean.equals("")){
                   iView.onIVSuccess(searchBean);
                }*/
                if(!o.equals("")){
                    iView.onIVSuccess(o);
                }
            }

            @Override
            public void onICFailder(String str) {
                //访问失败
                iView.onIVFailder(str);
            }
        },type);


    }

    //doget  参数commodityId 商品详情
    @Override
    public void doGetShopXQIP(int commodityId) {
        //定义一个泛型
        Type type = new TypeToken<DetailsBean>(){}.getType();

        //使用Model对象  调用网络请求方法
        model.doGetShopXQIM(API.APINewsUrl,commodityId,new ICallBack() {
            @Override
            public void onICSuccess(Object o) {
                //需要将数据强转  得到封装对象
                //SearchBean searchBean = (SearchBean) o;
                /*if(!searchBean.equals("")){
                   iView.onIVSuccess(searchBean);
                }*/
                if(!o.equals("")){
                    iView.onIVSuccess(o);
                }
            }

            @Override
            public void onICFailder(String str) {
                //访问失败
                iView.onIVFailder(str);
            }
        },type);
    }


    //解耦
    public void datach(){
       if(iView!=null){
           iView=null;
       }
       if(model!=null){
           model=null;
       }
    }
}
