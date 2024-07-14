package com.example.PersonalFinanceManagementSystem.service;

import com.example.PersonalFinanceManagementSystem.model.Income;
import com.example.PersonalFinanceManagementSystem.model.User;
import com.example.PersonalFinanceManagementSystem.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    public List<Income> getUserIncomes(User user) {
        return incomeRepository.findByUserOrderByDateDesc(user);
    }
}
