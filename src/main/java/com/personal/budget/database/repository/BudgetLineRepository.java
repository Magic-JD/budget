package com.personal.budget.database.repository;


import com.personal.budget.database.entity.BudgetLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetLineRepository extends JpaRepository<BudgetLineEntity, Long> {

}
