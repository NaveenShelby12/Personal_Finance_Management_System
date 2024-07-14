package com.example.PersonalFinanceManagementSystem.dto;

import com.example.PersonalFinanceManagementSystem.model.Expense;
import com.example.PersonalFinanceManagementSystem.model.Income;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FinancialOverview {
    private Integer income;
    private Integer expenses;
}
