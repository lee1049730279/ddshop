package com.zl.ddshop.search.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SerachIndex {

    @RequestMapping("/")
    public String portalIndex(Model model)
    {

        return "search";
    }
}
