package com.poshist.ca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
    @RequestMapping("/dictionaryList")
    public String dictionaryList(Long parentId, Model model){
        return "dictionary/dictionaryList";
    }
}
