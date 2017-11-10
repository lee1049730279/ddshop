package com.zl.ddshop.dao;

import com.zl.ddshop.common.dto.Order;
import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.pojo.vo.TbItemCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 自定义的商品实体数据访问层接口
 */
public interface TbItemCustomMapper {
    /**
     * 查询商品表中的所有记录的数量
     */
    int countItems( Map<String,Object> map);
    /**
     * 查询指定页面显示的记录集合
     */
    //多参数传递时，应该使用注解@Param
//    List<TbItemCustom> listItemsByPage(@Param("page") Page Page,@Param("order") Order order);
    List<TbItemCustom> listItemsByPage(Map<String,Object> map);
}
