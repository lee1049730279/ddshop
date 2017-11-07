package com.zl.ddshop.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class IndexAction {
    @RequestMapping("/")
    public  String IndexAction()
    {
        return  "index";
    }
    @RequestMapping("/{page}")
    public  String page(@PathVariable("page") String page)
    {
        return  page;
    }


}
