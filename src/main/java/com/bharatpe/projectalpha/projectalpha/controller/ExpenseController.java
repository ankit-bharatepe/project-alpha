package com.bharatpe.projectalpha.projectalpha.controller;

import com.bharatpe.projectalpha.projectalpha.manager.ExpenseManager;
import com.bharatpe.projectalpha.projectalpha.manager.TransactionManage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseManager expenseManager;


    @RequestMapping(value = "/get-active-expenses", method = RequestMethod.GET)
    public ResponseEntity<Object> activeExpenses() {
        return new ResponseEntity<>(expenseManager.getActiveExpenses(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add-subscription", method = RequestMethod.GET)
    public ResponseEntity<Object> addExpenses(@RequestParam(required = true) String type,
                                              @RequestParam(required = false) Double amount,
                                              @RequestParam(required = false) String bill_id,
                                              @RequestParam(required = true) String expense_id,
                                              @RequestParam(required = true) String merchant_id,
                                              @RequestParam(required = true) String bill_date,
                                              @RequestParam(required = false) String account_number,
                                              @RequestParam(required = false) String ifsc) {
        return new ResponseEntity<>(expenseManager.addExpenses(type, amount, bill_id, expense_id, merchant_id, bill_date, account_number, ifsc), HttpStatus.OK);
    }

    @RequestMapping(value = "/update-subscription", method = RequestMethod.GET)
    public ResponseEntity<Object> updateExpenses(@RequestParam(required = true) Long expense_agr_id,
                                                 @RequestParam(required = false) Double amount,
                                                @RequestParam(required = false) String bill_id,
                                                @RequestParam(required = false) String expense_id,
                                                @RequestParam(required = false) String bill_date,
                                                @RequestParam(required = false) String account_number,
                                                @RequestParam(required = false) String ifsc,
                                                 @RequestParam(required = false) String status) {
        return new ResponseEntity<>(expenseManager.updateExpenses(expense_agr_id, amount, bill_id, expense_id, bill_date, account_number, ifsc, status), HttpStatus.OK);
    }

}
