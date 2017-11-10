package com.zl.ddshop.service.impl;

import com.zl.ddshop.common.dto.Order;
import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.common.dto.Result;
import com.zl.ddshop.dao.TbItemCustomMapper;
import com.zl.ddshop.dao.TbItemMapper;
import com.zl.ddshop.pojo.po.TbItem;
import com.zl.ddshop.pojo.po.TbItemExample;
import com.zl.ddshop.pojo.vo.TbItemCustom;
import com.zl.ddshop.pojo.vo.TbItemQuery;
import com.zl.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result<TbItemCustom> listItemByPage(Page page, Order order, TbItemQuery tbItemQuery) {
        Result<TbItemCustom> result = null;
        try {
            //创建一个map封装前台传递过来的参数
            Map<String,Object> map=new HashMap<>();
            map.put("page",page);
            map.put("order",order);
            map.put("tbItemQuery",tbItemQuery);
            //创建一个响应参数实体类
            result = new Result<>();
            //对i经行设置（符合条件的总记录数
            int i = tbItemCustomMapper.countItems(map);
            result.setTotal(i);
            //对rows进行设置
//            List<TbItemCustom> tbItemCustoms = tbItemCustomMapper.listItemsByPage(page,order);
            List<TbItemCustom> tbItemCustoms = tbItemCustomMapper.listItemsByPage(map);
            result.setRows(tbItemCustoms);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public int downItemsByIds(List<Long> ids) {
        TbItem record=new TbItem();
        record.setStatus((byte)2);
        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(ids);
        return itemMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int upItemsByIds(List<Long> ids) {
        TbItem record=new TbItem();
        record.setStatus((byte) 1);
        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(ids);
        return itemMapper.updateByExampleSelective(record,example);
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
