package com.example.PersonalFinanceManagementSystem.controller;

import com.example.PersonalFinanceManagementSystem.model.Income;
import com.example.PersonalFinanceManagementSystem.service.IncomeService;
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
@RequestMapping("/api/incomes")
public class IncomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public ResponseEntity<?> addIncome(@Param("id") Long id, @RequestBody Income income) {
        income.setUser(userService.getUserById(id));
        income.setDate(LocalDate.now());
        Income savedIncome = incomeService.addIncome(income);
        return ResponseEntity.ok(savedIncome);
    }

    @GetMapping
    public ResponseEntity<List<Income>> getUserIncomes(@Param("id") Long id) {
        List<Income> incomes = incomeService.getUserIncomes(userService.getUserById(id));
        return ResponseEntity.ok(incomes);
    }
}
