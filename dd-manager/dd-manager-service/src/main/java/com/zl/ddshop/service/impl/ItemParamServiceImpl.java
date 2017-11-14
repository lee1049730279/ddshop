package com.zl.ddshop.service.impl;

import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.common.dto.Result;
import com.zl.ddshop.dao.TBItemParamCustomMapper;
import com.zl.ddshop.pojo.vo.TbItemParamCustom;
import com.zl.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ItemParamServiceImpl implements ItemParamService{
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TBItemParamCustomMapper tbItemParamCustomMapper;
    @Override
    public Result<TbItemParamCustom> listItemParamsByPage(Page page) {
        Result<TbItemParamCustom> result=null;
                //dao层接口多个参数解决方案之一

        try {
            Map<String,Object> map=new HashMap<>();
            map.put("page",page);
            //取出记录数
           int count= tbItemParamCustomMapper.countItemParams();

            //取出对应页码的记录集合
            List<TbItemParamCustom> list =tbItemParamCustomMapper.listItemParamsBypage(map);
            result=new Result<>();
            result.setTotal(count);
            result.setRows(list);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  result;
    }
}
