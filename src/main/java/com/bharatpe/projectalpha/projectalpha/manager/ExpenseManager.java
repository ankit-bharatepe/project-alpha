package com.bharatpe.projectalpha.projectalpha.manager;

import com.bharatpe.projectalpha.projectalpha.dao.ActiveExpensesDao;
import com.bharatpe.projectalpha.projectalpha.entity.ActiveExpenses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExpenseManager {

    @Autowired
    ActiveExpensesDao activeExpensesDao;

    Logger logger = LoggerFactory.getLogger(ExpenseManager.class);

    public Object getActiveExpenses(){
        Map<String, Object> response = new HashMap<>();

        try{
            List<ActiveExpenses> activeExpensesList = (List<ActiveExpenses>) activeExpensesDao.findAll();

            response.put("success", true);
            response.put("message", "active Expenses List");
            response.put("data", activeExpensesList);

            return response;
        }catch (Exception ex){
            logger.error("Error Occurred while getting  activeExpensesList Error: ", ex);
        }

        response.put("success", false);
        response.put("message", "SomeThing Went Wrong");
        response.put("data", new ArrayList<ActiveExpenses>());

        return response;
    }
}
