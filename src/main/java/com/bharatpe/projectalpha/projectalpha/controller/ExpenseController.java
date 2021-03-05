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
                                              @RequestParam(required = false) String provider_name,
                                              @RequestParam(required = true) String expense_id,
                                              @RequestParam(required = true) String merchant_id,
                                              @RequestParam(required = true) String bill_date,
                                              @RequestParam(required = false) String account_number,
                                              @RequestParam(required = false) String account_name,
                                              @RequestParam(required = false) String school_name,
                                              @RequestParam(required = false) String city,
                                              @RequestParam(required = false) String state,
                                              @RequestParam(required = false) String school_id,
                                              @RequestParam(required = false) String ifsc,
                                              @RequestParam(required = false) String employee_name,
                                              @RequestParam(required = false) String salary,
                                              @RequestParam(required = false) String name,
                                              @RequestParam(required = false) String transaction_title,
                                              @RequestParam(required = false) String house_number,
                                              @RequestParam(required = false) String house_address,
                                              @RequestParam(required = false) String other_details) {
        return new ResponseEntity<>(expenseManager.addExpenses(type, amount, bill_id, provider_name,  expense_id, merchant_id, bill_date, account_number, ifsc, account_name,  school_name, city, state, school_id, employee_name, salary, name, transaction_title, other_details, house_number, house_address), HttpStatus.OK);
    }

    @RequestMapping(value = "/update-subscription", method = RequestMethod.GET)
    public ResponseEntity<Object> updateExpenses(@RequestParam(required = true) Long expense_agr_id,
                                                 @RequestParam(required = false) Double amount,
                                                @RequestParam(required = false) String bill_id,
                                                @RequestParam(required = false) String expense_id,
                                                @RequestParam(required = false) String bill_date,
                                                 @RequestParam(required = false) String provider_name,
                                                 @RequestParam(required = false) String account_number,
                                                @RequestParam(required = false) String ifsc,
                                                 @RequestParam(required = false) String school_name,
                                                 @RequestParam(required = false) String account_name,
                                                 @RequestParam(required = false) String city,
                                                 @RequestParam(required = false) String state,
                                                 @RequestParam(required = false) String school_id,
                                                 @RequestParam(required = false) String employee_name,
                                                 @RequestParam(required = false) String salary,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String transaction_title,
                                                 @RequestParam(required = false) String other_details,
                                                 @RequestParam(required = false) String house_number,
                                                 @RequestParam(required = false) String house_address,
                                                 @RequestParam(required = false) String status) {
        return new ResponseEntity<>(expenseManager.updateExpenses(expense_agr_id, amount, bill_id, provider_name, expense_id, bill_date, account_number, ifsc, account_name,  school_name, city, state, school_id, employee_name, salary, name, transaction_title, other_details, house_number, house_address, status), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-active-merchant-expenses", method = RequestMethod.GET)
    public ResponseEntity<Object> activeMerchantExpenses(@RequestParam String merchantId) {
        return new ResponseEntity<>(expenseManager.getActiveMerchantExpenses(merchantId), HttpStatus.OK);
    }

}
