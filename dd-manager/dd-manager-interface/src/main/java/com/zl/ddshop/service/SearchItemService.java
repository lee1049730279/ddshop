package com.zl.ddshop.service;

import com.zl.ddshop.pojo.vo.TbSearchItemResult;

//将采集到的数据导入到索引库
public interface SearchItemService {
    boolean importAllItems();
    TbSearchItemResult search(String keyword, Integer page, int rows);
}
