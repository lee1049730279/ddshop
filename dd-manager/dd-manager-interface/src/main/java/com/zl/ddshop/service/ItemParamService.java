package com.zl.ddshop.service;

import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.common.dto.Result;
import com.zl.ddshop.pojo.vo.TbItemParamCustom;

public interface ItemParamService {
    /**
     * 对规格说明表进行分页
     * @param page
     * @return
     */
    Result<TbItemParamCustom> listItemParamsByPage(Page page);
}
