package com.example.PersonalFinanceManagementSystem.service;

import com.example.PersonalFinanceManagementSystem.model.Expense;
import com.example.PersonalFinanceManagementSystem.model.User;
import com.example.PersonalFinanceManagementSystem.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getUserExpenses(User user) {
        return expenseRepository.findByUserOrderByDateDesc(user);
    }
}
