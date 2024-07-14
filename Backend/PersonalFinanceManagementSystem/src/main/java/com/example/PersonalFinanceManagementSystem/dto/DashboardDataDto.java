package com.example.PersonalFinanceManagementSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class DashboardDataDto {
    private Integer totalIncome;
    private Integer totalExpenses;
    private Integer netSavings;
    private List<FinancialOverview> financialOverviewList;
}
