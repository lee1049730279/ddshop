package com.zl.ddshop.service;

import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.common.dto.Result;
import com.zl.ddshop.pojo.po.TbItem;
import com.zl.ddshop.pojo.vo.TbItemCustom;

import java.util.List;

public interface ItemService {

    TbItem getById(Long itemId);

//    List<TbItem> itemList();
    Result<TbItemCustom> listItemByPage(Page page);
}
