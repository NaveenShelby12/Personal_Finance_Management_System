package com.example.PersonalFinanceManagementSystem.controller;

import com.example.PersonalFinanceManagementSystem.model.Expense;
import com.example.PersonalFinanceManagementSystem.service.ExpenseService;
import com.example.PersonalFinanceManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private UserService userService;
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> addExpense(@Param("id") Long id, @RequestBody Expense expense) {
        System.out.println("id = " + id);
        expense.setUser(userService.getUserById(id));
        expense.setDate(LocalDate.now());
        Expense savedExpense = expenseService.addExpense(expense);
        return ResponseEntity.ok(savedExpense);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getUserExpenses(@Param("id") Long id) {
        List<Expense> expenses = expenseService.getUserExpenses(userService.getUserById(id));
        for(Expense expense:expenses) {
            System.out.println("expense = " + expense);;
        }
        return ResponseEntity.ok(expenses);
    }
}
