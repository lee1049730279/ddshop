package com.zl.ddshop.pojo.vo;

import com.zl.ddshop.pojo.po.TbItem;

/**
 * 自定义的商品显示类
 */
public class TbItemCustom extends TbItem {
    private String catName;

    private String priceView;

    public String getPriceView() {
        return priceView;
    }

    public void setPriceView(String priceView) {
        this.priceView = priceView;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
