package com.example.administrator.demo_zkl01.startfragmentpage.homepage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.demo_zkl01.startfragmentpage.homepage.bean.DetailsBean;

import java.util.List;

/**
 * Author : 张自力
 * Created on time.
 */

public class DetailsAdapter extends PagerAdapter {

    private Context mcontext;
    private List<DetailsBean.ResultBean> imgList;

    public DetailsAdapter(Context mcontext, List<DetailsBean.ResultBean> imgList) {
        this.mcontext = mcontext;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        //判断
        if(imgList.size()!=0){
            return imgList.size();
        }else{
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //覆写两个方法


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //创建一个视图对象
        ImageView imageView = new ImageView(mcontext);
        //从list中得到数据
        DetailsBean.ResultBean resultBean = imgList.get(position);
        //得到数据  进行剪切
        String picture = resultBean.getPicture();
        String[] split = picture.split(",");
        //使用Glide赋值给视图对象
        Glide.with(mcontext).load(split[0]).into(imageView);
        //加入
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
