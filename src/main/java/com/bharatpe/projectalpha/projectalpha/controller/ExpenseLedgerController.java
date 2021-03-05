package com.bharatpe.projectalpha.projectalpha.controller;

import com.bharatpe.projectalpha.projectalpha.entity.ExpenseLedger;
import com.bharatpe.projectalpha.projectalpha.manager.ExpenseLedgerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseLedgerController {

    @Autowired
    ExpenseLedgerManager expenseLedgerManager;

    @RequestMapping(value = "/merchant-expense-ledger" , method = RequestMethod.GET)
    public ResponseEntity<Object> getMerchantExpense(@RequestParam(required = true) String merchantId){
        return expenseLedgerManager.getExpenseDetail(merchantId);
    }

    @RequestMapping(value = "/merchant-expense-ledger-detail" , method = RequestMethod.GET)
    public ResponseEntity<Object> getDetail(@RequestParam(required = true) String merchantId ,@RequestParam(required = true) Long aggregatorId){
        return expenseLedgerManager.getDetail(merchantId,aggregatorId);
    }

}
