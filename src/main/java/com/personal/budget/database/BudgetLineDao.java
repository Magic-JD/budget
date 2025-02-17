package com.personal.budget.database;

import com.personal.budget.data.BudgetLine;
import com.personal.budget.data.LineType;
import com.personal.budget.data.Money;
import com.personal.budget.database.entity.BudgetLineEntity;
import com.personal.budget.database.repository.BudgetLineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Order;
import static org.springframework.data.domain.Sort.by;

@Component
public class BudgetLineDao {

    private final BudgetLineRepository repository;

    public BudgetLineDao(BudgetLineRepository repository) {
        this.repository = repository;
    }

    public void addBudgetLine(BudgetLine budgetLine, ZonedDateTime currentTime){
        repository.save(convertToEntry(budgetLine, currentTime));
    }

    public List<BudgetLine> getLatestEntries(int limit) {
        Pageable pageable = of(0, limit).first()
                .withSort(by(Order.by("date")).reverse());
        Page<BudgetLineEntity> latestPage = repository.findAll(pageable);
        return latestPage
                .stream()
                .map(this::convertToInternal)
                .toList();
    }

    private BudgetLine convertToInternal(BudgetLineEntity budgetLineEntity) {
        return new BudgetLine(
                LineType.valueOf(budgetLineEntity.getType()),
                new Money(budgetLineEntity.getAmount()),
                budgetLineEntity.getComment()
        );
    }

    private BudgetLineEntity convertToEntry(BudgetLine budgetLine, ZonedDateTime currentTime) {
        return BudgetLineEntity
                .builder()
                .type(budgetLine.type().name())
                .amount(budgetLine.money().amount())
                .comment(budgetLine.comment())
                .date(currentTime.toEpochSecond()).build();
    }
}
