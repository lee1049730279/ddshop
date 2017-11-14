package com.zl.ddshop.web;

import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.common.dto.Result;
import com.zl.ddshop.pojo.vo.TbItemParamCustom;
import com.zl.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamAction {
    private Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemParamService itemParamService;
    @ResponseBody
    @RequestMapping("/itemParams")
    public Result<TbItemParamCustom> listItemParams(Page page)
    {
        Result<TbItemParamCustom> result=null;

        try {
          result= itemParamService.listItemParamsByPage(page);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  result;
    }
}
