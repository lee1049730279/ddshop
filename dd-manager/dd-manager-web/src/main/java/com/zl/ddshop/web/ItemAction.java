package com.zl.ddshop.web;

import com.zl.ddshop.pojo.po.TbItem;
import com.zl.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class ItemAction {

    @Autowired
    private ItemService itemService;
    @ResponseBody
    @RequestMapping(value ="/item/{itemId}",method= RequestMethod.GET)
    public TbItem getById(@PathVariable("itemId") Long itemId)
    {
        System.out.println(itemId);
        return itemService.getById(itemId);
    }

    @RequestMapping("/{page}")
    public  String page(@PathVariable("page") String page)
    {
        return  page;
    }
}
