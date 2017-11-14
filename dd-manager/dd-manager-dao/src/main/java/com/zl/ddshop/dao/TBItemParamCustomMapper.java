package com.zl.ddshop.dao;

import com.zl.ddshop.pojo.vo.TbItemParamCustom;

import java.util.List;
import java.util.Map;

public interface TBItemParamCustomMapper {
    int countItemParams();

    List<TbItemParamCustom> listItemParamsBypage(Map<String, Object> map);
}
