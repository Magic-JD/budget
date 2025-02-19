package com.personal.budget.controller;

import com.personal.budget.data.BudgetLine;
import com.personal.budget.data.LineType;
import com.personal.budget.data.Money;
import com.personal.budget.service.BudgetService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PutMapping("out")
    @HxRequest
    String outItem(@RequestParam LineType type, @RequestParam long amount, @RequestParam String comment, Model model){
        BudgetLine budgetLine = new BudgetLine(type, new Money(amount * 1_000), comment);
        if(!budgetService.outBudget(budgetLine)){
            return "fail";
        }
        model.addAttribute("latest", budgetService.getLatestEntries());
        return "result";
    }
}
