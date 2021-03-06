package com.bharatpe.projectalpha.projectalpha.controller;

import com.bharatpe.projectalpha.projectalpha.manager.TransactionManage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TransactionController {

    @Autowired
    TransactionManage transactionManage;
    @RequestMapping(value = "/get-lucky-transaction", method = RequestMethod.GET)
    public ResponseEntity<Object> getLuckyTransaction(@RequestParam(name = "amount") String amount , @RequestParam(name = "name") String name) throws JsonProcessingException {
        return transactionManage.getTransaction(amount,name);

    }

    @RequestMapping(value = "/get-jin")
    public ResponseEntity<Object> getJinnyOrSpin() throws JsonProcessingException {
        return transactionManage.getJinny();
    }

    @RequestMapping(value = "/get-list")
    public ResponseEntity<Object> getBenefit(){
        return transactionManage.getList();
    }

    @RequestMapping(value = "/active-cashback")
    public ResponseEntity<Object> getActive(@RequestParam(name = "merchantId") String merchantId) throws JsonProcessingException {
        return transactionManage.getActiveCashback(merchantId);
    }

    @RequestMapping(value = "/save-active-cashback")
    public ResponseEntity<Object> getActive(@RequestParam(name = "merchantId") String merchantId , @RequestParam(name = "cashbackId") String cashbackId) throws JsonProcessingException {
        return transactionManage.setActiveCashback(merchantId,cashbackId);
    }

}
