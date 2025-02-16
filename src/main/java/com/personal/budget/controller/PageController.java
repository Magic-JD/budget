package com.personal.budget.controller;

import com.personal.budget.data.LineType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    String getPage(Model model){
        model.addAttribute("types", LineType.values());
        return "page";
    }

    @GetMapping("/login")
    String getLogin(){
        return "login";
    }
}
