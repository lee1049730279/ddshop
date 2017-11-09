package com.zl.ddshop.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * easyui的datagrid排序通用类
 */
public class Order {
    private String sort;
    private  String order;
//    private List<String> orderParams;

    public List<String> getOrderParams() {
        String[] sorts=sort.split(",");
        String[] orders=order.split(",");
        List<String> list=new ArrayList<>();
        for(int i=0;i<sorts.length;i++)
        {
            String s = sorts[i] + " " + orders[i];
            list.add(s);
        }
        return list;
    }

//    public void setOrderParams(List<String> orderParams) {
//        this.orderParams = orderParams;
//    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
