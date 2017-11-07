package com.zl.ddshop.service.impl;

import com.zl.ddshop.dao.TbItemMapper;
import com.zl.ddshop.pojo.po.TbItem;
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

    @Override
    public TbItem getById(Long itemId) {
        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        return tbItem;
    }

    @Override
    public List<TbItem> itemList() {
        List<TbItem> list = null;
        try {
            list = itemMapper.selectByExample(null);
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
           e.printStackTrace();
        }
        return  list;

    }
}
