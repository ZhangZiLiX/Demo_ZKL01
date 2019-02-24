package com.example.administrator.demo_zkl01;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.demo_zkl01.startfragmentpage.shoppingpage.FragmentShopping;
import com.example.administrator.demo_zkl01.startfragmentpage.homepage.FragmentHome;
import com.example.administrator.demo_zkl01.startfragmentpage.mepage.FragmentMe;
import com.example.administrator.demo_zkl01.startfragmentpage.circlepage.FragmentCircle;
import com.example.administrator.demo_zkl01.startfragmentpage.billpage.FragmentBill;
import com.example.administrator.demo_zkl01.utils.BaseActionBar;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActionBar {

    @BindView(R.id.main_btn_vp)
    ViewPager mainBtnVp;
    @BindView(R.id.btn_home)
    Button btnHome;
    @BindView(R.id.btn_quanzi)
    Button btnQuanzi;
    @BindView(R.id.btn_bycar)
    Button btnBycar;
    @BindView(R.id.btn_zhangdan)
    Button btnZhangdan;
    @BindView(R.id.btn_me)
    Button btnMe;
    private Unbinder butterKnife;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定ButterKnife
        butterKnife = ButterKnife.bind(this);
        //初始化底部导航栏按钮颜色
        initBtnColor();
        //初始化list 和 Fragment
        initListAndFragment();
        //vp设置
        setViewPagerFragment();
        //侧滑设置
        initSlidingMenu();

    }

    //侧滑设置
    private void initSlidingMenu() {
        SlidingMenu menu = new SlidingMenu(this);

        //设置侧滑方法
        menu.setMode(SlidingMenu.LEFT);
        //设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置华东菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.cardview_default_radius);
        //设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);

        //把菜单添加进所有的Activity中  可选值SLIDING_CONTENT 会导致Activity界面混乱  ,SLIDING_WINDOW
        menu.attachToActivity(this,SlidingMenu.SLIDING_WINDOW);
        //设置滑动后剩余部分
        menu.setBehindOffset(150);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.slidingmenu);
    }


    //vp设置
    private void setViewPagerFragment() {
        mainBtnVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        mainBtnVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               //设置一个监听方法
               setOnChangerListener(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    //Fragment按钮改变监听
    private void setOnChangerListener(int position) {
       btnHome.setTextColor(position==0?Color.RED:Color.BLACK);
       btnQuanzi.setTextColor(position==1?Color.RED:Color.BLACK);
       btnBycar.setTextColor(position==2?Color.RED:Color.BLACK);
       btnZhangdan.setTextColor(position==3?Color.RED:Color.BLACK);
       btnMe.setTextColor(position==4?Color.RED:Color.BLACK);
    }

    //初始化list 和 Fragment
    private void initListAndFragment() {
        //存放Fragment 对象
        fragmentList = new ArrayList<>();
        //初始化Fragment
        FragmentHome fragment_home = new FragmentHome();
        FragmentCircle fragment_quanZi = new FragmentCircle();
        FragmentShopping fragment_gouWuChe = new FragmentShopping();
        FragmentBill fragment_zhangDan = new FragmentBill();
        FragmentMe fragment_me = new FragmentMe();
        //添加进FragmentList
        fragmentList.add(fragment_home);
        fragmentList.add(fragment_quanZi);
        fragmentList.add(fragment_gouWuChe);
        fragmentList.add(fragment_zhangDan);
        fragmentList.add(fragment_me);
    }

    //底部导航栏点击事件
    @OnClick({R.id.btn_home, R.id.btn_quanzi, R.id.btn_bycar, R.id.btn_zhangdan, R.id.btn_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                mainBtnVp.setCurrentItem(0);
                setOnChangerListener(0);
                break;
            case R.id.btn_quanzi:
                mainBtnVp.setCurrentItem(1);
                setOnChangerListener(1);
                break;
            case R.id.btn_bycar:
                mainBtnVp.setCurrentItem(2);
                setOnChangerListener(2);
                break;
            case R.id.btn_zhangdan:
                mainBtnVp.setCurrentItem(3);
                setOnChangerListener(3);
                break;
            case R.id.btn_me:
                mainBtnVp.setCurrentItem(4);
                setOnChangerListener(4);
                break;
        }
    }

    //初始化底部导航栏按钮颜色
    private void initBtnColor() {
        btnHome.setTextColor(Color.RED);
        btnQuanzi.setTextColor(Color.BLACK);
        btnBycar.setTextColor(Color.BLACK);
        btnZhangdan.setTextColor(Color.BLACK);
        btnMe.setTextColor(Color.BLACK);
    }

    //销毁方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑ButterKnife
        butterKnife.unbind();
    }
}
