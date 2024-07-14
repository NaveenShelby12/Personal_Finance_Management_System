package com.example.PersonalFinanceManagementSystem.service;

import com.example.PersonalFinanceManagementSystem.dto.DashboardDataDto;
import com.example.PersonalFinanceManagementSystem.dto.FinancialOverview;
import com.example.PersonalFinanceManagementSystem.model.Expense;
import com.example.PersonalFinanceManagementSystem.model.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private UserService userService;
    public DashboardDataDto getDashboardData(Long userId) {
        List<Income> incomeList = incomeService.getUserIncomes(userService.getUserById(userId));
        List<Expense> expenseList = expenseService.getUserExpenses(userService.getUserById(userId));
        Integer totalIncome = incomeList.stream().map(income -> income.getAmount())
                .mapToInt(BigDecimal::intValue).reduce(0, Integer::sum);
        Integer totalExpenses = expenseList.stream().map(expense -> expense.getAmount())
                .mapToInt(BigDecimal::intValue).reduce(0, Integer::sum);
        Integer savings = totalIncome - totalExpenses;
        DashboardDataDto dashboardData = new DashboardDataDto();
        dashboardData.setTotalIncome(totalIncome);
        dashboardData.setTotalExpenses(totalExpenses);
        dashboardData.setNetSavings(savings);
        List<FinancialOverview> financialOverviewList = new ArrayList<>();
        financialOverviewList.add(new FinancialOverview(totalIncome, totalExpenses));
        dashboardData.setFinancialOverviewList(financialOverviewList);
        return dashboardData;
    }
}
