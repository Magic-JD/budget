package com.personal.budget.data;

public record BudgetLine(LineType type, Money money, String comment) { }
