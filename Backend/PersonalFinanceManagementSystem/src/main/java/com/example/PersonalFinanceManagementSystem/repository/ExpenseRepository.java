package com.example.PersonalFinanceManagementSystem.repository;

import com.example.PersonalFinanceManagementSystem.model.Expense;
import com.example.PersonalFinanceManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserIdOrderByDateDesc(Long userId);

    List<Expense> findByUserOrderByDateDesc(User user);
}
