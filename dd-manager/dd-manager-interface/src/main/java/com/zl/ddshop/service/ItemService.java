package com.zl.ddshop.service;

import com.zl.ddshop.pojo.po.TbItem;

import java.util.List;

public interface ItemService {

    TbItem getById(Long itemId);

    List<TbItem> itemList();
}
