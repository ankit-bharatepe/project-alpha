package com.bharatpe.projectalpha.projectalpha.controller;

import com.bharatpe.projectalpha.projectalpha.manager.TransactionManage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionManage transactionManage;
    @RequestMapping(value = "/get-lucky-transaction", method = RequestMethod.GET)
    public ResponseEntity<Object> getLuckyTransaction(@RequestParam(name = "amount") String amount , @RequestParam(name = "name") String name) throws JsonProcessingException {
        return transactionManage.getTransaction(amount,name);

    }
}
