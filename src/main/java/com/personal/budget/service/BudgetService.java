package com.personal.budget.service;

import com.personal.budget.data.BudgetLine;
import com.personal.budget.clients.google.SheetClient;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    private final SheetClient sheetClient;

    public BudgetService(SheetClient sheetClient) {
        this.sheetClient = sheetClient;
    }

    public boolean outBudget(BudgetLine budgetLine){
        return sheetClient.updateSheet(budgetLine);
    }

}
