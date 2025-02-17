package com.personal.budget.controller;

import com.personal.budget.data.BudgetLine;
import com.personal.budget.data.LineType;
import com.personal.budget.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    private BudgetService budgetService;

    public PageController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/")
    String getPage(Model model){
        List<BudgetLine> latestEntries = budgetService.getLatestEntries();
        model.addAttribute("types", LineType.values());
        model.addAttribute("latest", latestEntries);
        return "page";
    }

    @GetMapping("/login")
    String getLogin(){
        return "login";
    }
}
