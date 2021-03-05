package com.bharatpe.projectalpha.projectalpha.manager;

import com.bharatpe.projectalpha.projectalpha.dao.DummyTransactionDao;
import com.bharatpe.projectalpha.projectalpha.entity.DummyTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransactionManage {

    @Autowired
    DummyTransactionDao dummyTransactionDao;

    @Autowired
    ObjectMapper objectMapper;

    public ResponseEntity<Object> getTransaction() throws JsonProcessingException {
        DummyTransaction dummyTransaction = new DummyTransaction();

        List<DummyTransaction> dummyTransactionList = dummyTransactionDao.getAllTransaction();
        Map<String,String> response = new HashMap<>();
        response.put("jin" , null);
        response.put("spin" , null);
        if(dummyTransactionList.size()%3==0){
            response.put("jin","true");
        }else if(dummyTransactionList.size()%5==0){
            response.put("spin","true");
        }

        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.OK);
    }
}
