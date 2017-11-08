package com.zl.ddshop.web;

import com.zl.ddshop.common.dto.Page;
import com.zl.ddshop.common.dto.Result;
import com.zl.ddshop.pojo.po.TbItem;
import com.zl.ddshop.pojo.vo.TbItemCustom;
import com.zl.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("prototype")
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem getById(@PathVariable("itemId") Long itemId) {
        System.out.println(itemId);
        return itemService.getById(itemId);
    }

//    @ResponseBody
//    @RequestMapping("/items")
//    public List<TbItem> itemList() {
//        List<TbItem> list = null;
//        try {
//            list = itemService.itemList();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            e.printStackTrace();
//        }
//        return list;
//
//    }
    @ResponseBody
    @RequestMapping("/items")
    public Result<TbItemCustom> listItemByPage(Page page)
    {
        Result<TbItemCustom> result = null;
        try {
            result=itemService.listItemByPage(page);
        }catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
             return  result;

    }
    @ResponseBody
    @RequestMapping(value = "/items/batch",method = RequestMethod.POST)
    public int updateItemsByIds(@RequestParam("ids[]") List<Long> ids)
    {
//        System.out.println(ids.size());
//        return 2;
        return itemService.updateItemsByIds(ids);
    }
}
