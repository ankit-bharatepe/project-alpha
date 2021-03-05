package com.bharatpe.projectalpha.projectalpha.controller;

import com.bharatpe.projectalpha.projectalpha.manager.ExpenseManager;
import com.bharatpe.projectalpha.projectalpha.manager.TransactionManage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseManager expenseManager;


    @RequestMapping(value = "/get-active-expenses", method = RequestMethod.GET)
    public ResponseEntity<Object> activeExpenses() {
        return new ResponseEntity<>(expenseManager.getActiveExpenses(), HttpStatus.OK);
    }
}
