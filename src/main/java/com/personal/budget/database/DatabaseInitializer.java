package com.personal.budget.database;

import com.personal.budget.clients.google.SheetClient;
import com.personal.budget.data.BudgetLine;
import jakarta.persistence.EntityManager;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class DatabaseInitializer {

    private final EntityManager entityManager;
    private final SheetClient sheetClient;
    private final BudgetLineDao budgetLineDao;

    public DatabaseInitializer(EntityManager entityManager, SheetClient sheetClient, BudgetLineDao budgetLineDao) {
        this.entityManager = entityManager;
        this.sheetClient = sheetClient;
        this.budgetLineDao = budgetLineDao;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void onApplicationEvent() {
        if (budgetLineDao.isEmpty()) {
            List<BudgetLine> budgetLines = sheetClient.getCurrentData().reversed();
            budgetLines.forEach(budgetLine -> budgetLineDao.addBudgetLine(budgetLine, ZonedDateTime.now()));
        }
    }
}
