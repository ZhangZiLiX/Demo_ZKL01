package com.example.administrator.demo_zkl01.startfragmentpage.homepage.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author : 张自力
 * Created on time.
 *
 *  搜索框  数据库存储 封装类
 *
 */

@Entity
public class DaoSearch {

    @Id
    private Long id;
    private String commodityName;//标题
    private String masterPic;//图片
    private int price;//价格
    private int saleNum;//销量
    @Generated(hash = 1211505702)
    public DaoSearch(Long id, String commodityName, String masterPic, int price,
            int saleNum) {
        this.id = id;
        this.commodityName = commodityName;
        this.masterPic = masterPic;
        this.price = price;
        this.saleNum = saleNum;
    }
    @Generated(hash = 1561287334)
    public DaoSearch() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCommodityName() {
        return this.commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getMasterPic() {
        return this.masterPic;
    }
    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getSaleNum() {
        return this.saleNum;
    }
    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

}
