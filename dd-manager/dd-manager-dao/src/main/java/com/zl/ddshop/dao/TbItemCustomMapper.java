package com.zl.ddshop.dao;

import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.pojo.vo.TbItemCustom;

import java.util.List;

/**
 * 自定义的商品实体数据访问层接口
 */
public interface TbItemCustomMapper {
    /**
     * 查询商品表中的所有记录的数量
     */
    int countItems();
    /**
     * 查询指定页面显示的记录集合
     */
    List<TbItemCustom> listItemsByPage(Page Page);
}
