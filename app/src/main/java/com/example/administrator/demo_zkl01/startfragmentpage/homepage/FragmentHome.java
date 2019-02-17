package com.example.administrator.demo_zkl01.startfragmentpage.homepage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo_zkl01.R;
import com.example.administrator.demo_zkl01.application.MyApplication;
import com.example.administrator.demo_zkl01.dao.DaoSearchDao;
import com.example.administrator.demo_zkl01.presenter.Presenter;
import com.example.administrator.demo_zkl01.startfragmentpage.homepage.adapter.SearchAdapter;
import com.example.administrator.demo_zkl01.startfragmentpage.homepage.bean.DaoSearch;
import com.example.administrator.demo_zkl01.startfragmentpage.homepage.bean.DetailsBean;
import com.example.administrator.demo_zkl01.startfragmentpage.homepage.bean.SearchBean;
import com.example.administrator.demo_zkl01.utils.API;
import com.example.administrator.demo_zkl01.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Author : 张自力
 * Created on time.
 * <p>
 * Fragment首页
 */

public class FragmentHome extends Fragment implements View.OnClickListener, IView {

    private View view;
    /**
     * 菜单
     */
    private TextView txtMenu;
    private SearchView searchview;
    /**
     * 搜索
     */
    private TextView txtSerach;
    private XRecyclerView xlvSearch;
    /**
     * 返回
     */
    private TextView mTxtSerachCack;
    private Presenter presenter;
    private List<SearchBean.ResultBean> searchList;
    private SearchAdapter searchAdapter;
    private DaoSearchDao daoSearchDao;
    private long insert;
    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        //初始化控件
        initView(view);
        //初始化list 和 adapter
        initListAndAdapter(view);
        //网络判断初始化
        initSetNetWork(view);
        //初始化Presenter
        initPresenter(view);
        //点击事件监听
        setBtnOnClickListener(view);
        //搜索框SerachView设置
        initSearchViewListener(view);
        //条目点击事件
        initAdapterItemListener();
        return view;
    }

    //条目点击事件
    private void initAdapterItemListener() {
        //搜索框条目点击事件
        searchAdapter.setSearchOnClickListener(new SearchAdapter.searchOnClickListener() {
            @Override
            public void onChangeCommodityid(int commodityid) {
                Toast.makeText(getActivity(), "选择了商品" + commodityid, Toast.LENGTH_SHORT).show();
                 //点击详情后  将搜索列表隐藏  展示详情
                 xlvSearch.setVisibility(View.GONE);
                 mWebView.setVisibility(View.VISIBLE);
                //点击进行商品详情网络请求方法
                presenter.doGetShopXQIP(commodityid);
            }
        });
    }

    //搜索框SerachView设置
    //创建一个变量用来存储搜索内容
    String SearchContent = "";

    private void initSearchViewListener(View view) {
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchContent = newText;
                return true;
            }
        });


    }

    //点击事件监听
    private void setBtnOnClickListener(View view) {
        //点击搜索按钮
        txtMenu.setOnClickListener(this);
        txtSerach.setOnClickListener(this);
        mTxtSerachCack.setOnClickListener(this);
    }

    //初始化Presenter
    private void initPresenter(View view) {
        presenter = new Presenter();
        presenter.attach(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.txt_menu:

                break;
            case R.id.txt_serach://点击搜索
                //先将展示列表进行展示 如果有首页的列表这时应该设置为隐藏状态 本demo中没有那个模块所以不用设置
                xlvSearch.setVisibility(View.VISIBLE);
                //将搜索按钮隐藏,展示返回按钮
                txtSerach.setVisibility(View.GONE);
                mTxtSerachCack.setVisibility(View.VISIBLE);
                // 进行数据P层的请求
                //判断用户是否输入
                if (SearchContent.equals("")) {
                    //如果为空 就进行提示
                    Toast.makeText(getActivity(), "请输入要搜索的商品名称!", Toast.LENGTH_SHORT).show();
                } else {
                    //不为空就进行搜索
                    presenter.doGetIP(SearchContent);
                }
                break;
            case R.id.txt_serach_cack://点击返回按钮  返回到主页
                // 将搜索返回按钮隐藏
                txtSerach.setVisibility(View.VISIBLE);
                mTxtSerachCack.setVisibility(View.GONE);
                xlvSearch.setVisibility(View.GONE);
                SearchContent = "";
                break;

        }
    }

    //初始化list 和 adapter
    private void initListAndAdapter(View view) {
        searchList = new ArrayList<>();
        //adapter
        searchAdapter = new SearchAdapter(getActivity(), searchList);
        xlvSearch.setAdapter(searchAdapter);
    }

    //网络判断初始化
    private void initSetNetWork(View view) {
        boolean initNetWork = MyApplication.initNetWork(getActivity());
        if (!initNetWork) {//如果没有网 就从数据库中读取 并提示用户
            Toast.makeText(getActivity(), "网络异常,请检查您的网络!", Toast.LENGTH_LONG).show();
            //通过到对象 查找所有数据
            List<DaoSearch> daoSearches = daoSearchDao.loadAll();
            /*SearchBean searchBean = new SearchBean();
            List<SearchBean.ResultBean> resultBeanList = searchBean.getResult();*/
            //创建一个集合用来存储数据
            List<SearchBean.ResultBean> resultBeanList = new ArrayList<>();
            for (int i = 0; i < daoSearches.size(); i++) {
                //得到数据库中的对象
                DaoSearch daoSearch = daoSearches.get(i);
                //创建一个集合存储对象
                SearchBean.ResultBean resultBean = new SearchBean.ResultBean();
                //存数据
                resultBean.setCommodityName(daoSearch.getCommodityName());
                resultBean.setMasterPic(daoSearch.getMasterPic());
                resultBean.setPrice(daoSearch.getPrice());
                resultBean.setSaleNum(daoSearch.getSaleNum());
                //将对象加入集合
                resultBeanList.add(resultBean);
            }
            //将得到的集合存入集合  并刷新adapter
            searchList.clear();
            searchList.addAll(resultBeanList);
            searchAdapter.notifyDataSetChanged();

        } else {
            Toast.makeText(getActivity(), "网络正常!", Toast.LENGTH_LONG).show();
        }
    }

    //初始化控件
    private void initView(View view) {
        //初始化Dao对象
        daoSearchDao = MyApplication.getDaoSession().getDaoSearchDao();

        //头部控件
        txtMenu = (TextView) view.findViewById(R.id.txt_menu);//菜单
        searchview = (SearchView) view.findViewById(R.id.searchview);//搜索框
        txtSerach = (TextView) view.findViewById(R.id.txt_serach);//搜索按钮
        mTxtSerachCack = (TextView) view.findViewById(R.id.txt_serach_cack);//返回按钮
        //搜索内容列表展示
        xlvSearch = (XRecyclerView) view.findViewById(R.id.xlv_search);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        xlvSearch.setLayoutManager(gridLayoutManager);


        mWebView = (WebView) view.findViewById(R.id.webView);
    }

    //实现接口后实现的方法
    @Override
    public void onIVSuccess(Object o) {


        if (o instanceof SearchBean) {
            SearchBean searchBean = (SearchBean) o;
            if (searchBean.getResult().size() != 0) {
                searchList.clear();
                searchList.addAll(searchBean.getResult());
                searchAdapter.notifyDataSetChanged();
            }

            //将数据存入数据库
            if (searchBean.getResult().size() != 0) {
                //得到数据集合
                List<SearchBean.ResultBean> result = searchBean.getResult();
                //遍历集合
                for (int i = 0; i < result.size(); i++) {
                    SearchBean.ResultBean resultBean = result.get(i);
                    //创建一个对象  存储对象
                    DaoSearch daoSearch = new DaoSearch();
                    daoSearch.setCommodityName(resultBean.getCommodityName());
                    daoSearch.setMasterPic(resultBean.getMasterPic());
                    daoSearch.setPrice(resultBean.getPrice());
                    daoSearch.setSaleNum(resultBean.getSaleNum());
                    //调用dao对象  添加数据
                    insert = daoSearchDao.insert(daoSearch);
                }
                if (insert != 0) {
                    Toast.makeText(getActivity(), "数据添加成功!", Toast.LENGTH_SHORT).show();
                }
            }
        }

        //判断是否是商品详情类
        if (o instanceof DetailsBean) {
            DetailsBean detailsBean = (DetailsBean) o;
            DetailsBean.ResultBean detailsBeanResult = detailsBean.getResult();
            String details = detailsBeanResult.getDetails();
            //使用webview展示
            //String detailsURL= "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&count=10&keyword="+commodityid;
            //mWebView.loadUrl(details);


            mWebView.setWebViewClient(new WebViewClient());
            //使用简单的loadData()方法总会导致乱码，有可能是Android API的Bug
            //webView.loadData(data, "text/html", "GBK");
            mWebView.loadDataWithBaseURL(null, details, "text/html", "utf-8", null);


            //加上下面这段代码可以使网页中的链接不以浏览器的方式打开
            mWebView.setWebViewClient(new WebViewClient());
            //得到webview设置
            WebSettings webSettings = mWebView.getSettings();
            //允许使用javascript
            webSettings.setJavaScriptEnabled(true);
            //设置可自由缩放网页
            mWebView.getSettings().setSupportZoom(true);
            mWebView.getSettings().setBuiltInZoomControls(true);
            // 如果页面中链接，如果希望点击链接继续在当前browser中响应，
            // 而不是新开Android的系统browser中响应该链接，必须覆盖webview的WebViewClient对象
            mWebView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }

    @Override
    public void onIVFailder(String str) {
        Toast.makeText(getActivity(), "数据请求失败!", Toast.LENGTH_SHORT).show();
    }

    //注销
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter = null;
        }
    }
}
