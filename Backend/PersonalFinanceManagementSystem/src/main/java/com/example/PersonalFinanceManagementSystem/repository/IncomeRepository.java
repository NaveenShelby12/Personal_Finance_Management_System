package com.example.PersonalFinanceManagementSystem.repository;

import com.example.PersonalFinanceManagementSystem.model.Income;
import com.example.PersonalFinanceManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserIdOrderByDateDesc(Long userId);

    List<Income> findByUserOrderByDateDesc(User user);
}
