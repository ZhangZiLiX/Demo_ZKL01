package com.example.administrator.demo_zkl01.startfragmentpage.homepage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.demo_zkl01.R;
import com.example.administrator.demo_zkl01.startfragmentpage.homepage.bean.SearchBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Author : 张自力
 * Created on time.
 *
 * 首页搜索框的适配器
 *
 */

public class SearchAdapter extends XRecyclerView.Adapter<SearchAdapter.ViewHolder> {

    //定义一个接口
    public interface searchOnClickListener{
        void onChangeCommodityid(int commodityid);
    }
    //提供一个对象
    public searchOnClickListener searchOnClickListener;
    //对外提供一个方法
    public void setSearchOnClickListener(searchOnClickListener listener){
        searchOnClickListener = listener;
    }


    private Context mcontext;
    private List<SearchBean.ResultBean> searchList;

    public SearchAdapter(Context mcontext, List<SearchBean.ResultBean> searchList) {
        this.mcontext = mcontext;
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mcontext,R.layout.search_adapter,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(searchList.size()!=0){
            Glide.with(mcontext).load(searchList.get(position).getMasterPic()).into(holder.imgSearch);
            holder.txtSearchTitle.setText(searchList.get(position).getCommodityName());
            holder.txtSearchPrice.setText(searchList.get(position).getPrice()+"");
            holder.txtSearchShou.setText(searchList.get(position).getSaleNum()+"");
        }

        //条目点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用接口对象传值
                searchOnClickListener.onChangeCommodityid(searchList.get(position).getCommodityId());
            }
        });


    }

    @Override
    public int getItemCount() {
        if(searchList.size()!=0){
            return searchList.size();
        }else{
            return 0;
        }
    }

    class ViewHolder extends XRecyclerView.ViewHolder {

        private final ImageView imgSearch;
        private final TextView txtSearchTitle;
        private final TextView txtSearchShou;
        private final TextView txtSearchPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            imgSearch = itemView.findViewById(R.id.img_search);
            txtSearchTitle = itemView.findViewById(R.id.txt_serach_title);
            txtSearchPrice = itemView.findViewById(R.id.txt_search_price);
            txtSearchShou = itemView.findViewById(R.id.txt_search_shou);
        }
    }


}
