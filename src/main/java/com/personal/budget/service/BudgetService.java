package com.personal.budget.service;

import com.personal.budget.data.BudgetLine;
import com.personal.budget.clients.google.SheetClient;
import com.personal.budget.database.BudgetLineDao;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class BudgetService {

    private final SheetClient sheetClient;
    private final BudgetLineDao budgetLineDao;

    public BudgetService(SheetClient sheetClient, BudgetLineDao budgetLineDao) {
        this.sheetClient = sheetClient;
        this.budgetLineDao = budgetLineDao;
    }

    public boolean outBudget(BudgetLine budgetLine){
        ZonedDateTime now = ZonedDateTime.now();
        budgetLineDao.addBudgetLine(budgetLine, now);
        return sheetClient.updateSheet(budgetLine, now);
    }

    public List<BudgetLine> getLatestEntries(){
        return budgetLineDao.getLatestEntries(5);
    }

}
