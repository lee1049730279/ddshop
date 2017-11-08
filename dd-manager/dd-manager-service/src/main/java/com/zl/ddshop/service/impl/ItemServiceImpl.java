package com.zl.ddshop.service.impl;

import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.common.dto.Result;
import com.zl.ddshop.dao.TbItemCustomMapper;
import com.zl.ddshop.dao.TbItemMapper;
import com.zl.ddshop.pojo.po.TbItem;
import com.zl.ddshop.pojo.po.TbItemExample;
import com.zl.ddshop.pojo.vo.TbItemCustom;
import com.zl.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemCustomMapper tbItemCustomMapper;

    @Override
    public TbItem getById(Long itemId) {
        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        return tbItem;
    }

//    @Override

    @Override
    public Result<TbItemCustom> listItemByPage(Page page) {
        Result<TbItemCustom> result = null;
        try {
            result = new Result<>();
            int i = tbItemCustomMapper.countItems();
            result.setTotal(i);
            List<TbItemCustom> tbItemCustoms = tbItemCustomMapper.listItemsByPage(page);
            result.setRows(tbItemCustoms);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }


        return result;
    }
    @Override
    public int updateItemsByIds(List<Long> ids) {
        TbItem record=new TbItem();
        record.setStatus((byte) 3);
        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(ids);
        return itemMapper.updateByExampleSelective(record,example);
    }
//    public List<TbItem> itemList() {
//        List<TbItem> list = null;
//        try {
//            list = itemMapper.selectByExample(null);
//        } catch (Exception e) {
//           logger.error(e.getMessage(),e);
//           e.printStackTrace();
//        }
//        return  list;
//
//    }
}
