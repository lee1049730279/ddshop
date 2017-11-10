package com.zl.ddshop.service;

import com.zl.ddshop.common.dto.Order;
import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.common.dto.Result;
import com.zl.ddshop.pojo.po.TbItem;
import com.zl.ddshop.pojo.vo.TbItemCustom;
import com.zl.ddshop.pojo.vo.TbItemQuery;

import java.util.List;

public interface ItemService {

    TbItem getById(Long itemId);

//    List<TbItem> itemList();
    Result<TbItemCustom> listItemByPage(Page page, Order order, TbItemQuery tbItemQuery);

    int updateItemsByIds(List<Long> ids);

    int downItemsByIds(List<Long> ids);

    int upItemsByIds(List<Long> ids);
}
