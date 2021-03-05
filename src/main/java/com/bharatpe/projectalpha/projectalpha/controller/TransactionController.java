package com.bharatpe.projectalpha.projectalpha.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @RequestMapping(value = "/get-lucky-transaction", method = RequestMethod.GET)
    public ResponseEntity<Object> getLuckyTransaction(){
        return new ResponseEntity<>("OK" , HttpStatus.OK);
    }
}
