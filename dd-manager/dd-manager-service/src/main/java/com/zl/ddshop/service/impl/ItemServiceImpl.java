package com.zl.ddshop.service.impl;

import com.zl.ddshop.common.dto.Order;
import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.common.dto.Result;
import com.zl.ddshop.common.util.IDUtils;
import com.zl.ddshop.dao.TbItemCustomMapper;
import com.zl.ddshop.dao.TbItemDescMapper;
import com.zl.ddshop.dao.TbItemMapper;
import com.zl.ddshop.pojo.po.TbItem;
import com.zl.ddshop.pojo.po.TbItemDesc;
import com.zl.ddshop.pojo.po.TbItemExample;
import com.zl.ddshop.pojo.vo.TbItemCustom;
import com.zl.ddshop.pojo.vo.TbItemQuery;
import com.zl.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

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

    //加上注解@Trabsactional之后，这个方法就变成了事务方法
    @Transactional
    @Override
    public int saveItem(TbItem tbItem, String content) {
        int i=0;
        try {
          //这个方法处理2张表格，tb_item，tb_item_desc
            Long itemId = IDUtils.getItemId();
            tbItem.setId(itemId);
            tbItem.setStatus((byte)2);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());
            i=itemMapper.insert(tbItem);

            //处理tb_item_desc
            TbItemDesc tbItemDesc=new TbItemDesc();
            tbItemDesc.setItemId(itemId);
            tbItemDesc.setItemDesc(content);
            tbItemDesc.setCreated(new Date());
            tbItemDesc.setUpdated(new Date());
            tbItemDescMapper.insert(tbItemDesc);
        }catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  i;
    }
}
