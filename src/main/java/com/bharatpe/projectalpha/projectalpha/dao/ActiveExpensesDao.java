package com.bharatpe.projectalpha.projectalpha.dao;

import com.bharatpe.projectalpha.projectalpha.entity.ActiveExpenses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveExpensesDao extends CrudRepository<ActiveExpenses, Long> {

    ActiveExpenses findTopByExpenseType(String expenseType);
}
