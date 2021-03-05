package com.bharatpe.projectalpha.projectalpha.manager;

import com.bharatpe.common.dao.MerchantBankDetailDao;
import com.bharatpe.common.dao.MerchantDao;
import com.bharatpe.common.entities.Merchant;
import com.bharatpe.common.entities.MerchantBankDetail;
import com.bharatpe.common.enums.NotificationProvider;
import com.bharatpe.common.handlers.SmsServiceHandler;
import com.bharatpe.projectalpha.projectalpha.dao.DummyTransactionDao;
import com.bharatpe.projectalpha.projectalpha.entity.DummyTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionManage {

    @Autowired
    DummyTransactionDao dummyTransactionDao;

    @Autowired
    ObjectMapper objectMapper;


    @Autowired
    SmsServiceHandler smsServiceHandler;


    public ResponseEntity<Object> getTransaction(String amount , String name) throws JsonProcessingException {
        DummyTransaction dummyTransaction = new DummyTransaction();
        dummyTransaction.setOrderId(12345L);
        dummyTransaction.setMerchantId(1L);
        dummyTransaction.setCreditedTo("");
        Long id = dummyTransaction.getMerchantId();
        dummyTransaction.setDebitedFrom(id.toString());
        dummyTransaction.setTxnType("credit");
        dummyTransaction.setStatus("active");
        dummyTransactionDao.save(dummyTransaction);
        List<DummyTransaction> dummyTransactionList = dummyTransactionDao.getAllTransaction();
        Map<String,String> response = new HashMap<>();
        response.put("jin" , null);
        response.put("spin" , null);
        List<String> mobile = new ArrayList<String>() {{
            add("918431726188");
        }};
        String smss = "Dear " + "Ankit Mishra" + ", you received a transaction of " + amount + "from " + name ;
        smsServiceHandler.sendSMS(mobile, smss, NotificationProvider.SMS.GUPSHUP);
        if(dummyTransactionList.size()%3==0){
            response.put("jin","true");
            List<String> mobiles = new ArrayList<>() {{
                add("918431726188");
            }};
            String sms = "Dear " + "Ankit Mishra" + ", you got a jinny on your transaction of amount " + amount + " from " + name;
            smsServiceHandler.sendSMS(mobiles, sms, NotificationProvider.SMS.GUPSHUP);

        }else if(dummyTransactionList.size()%5==0){
            response.put("spin","true");
            List<String> mobiles = new ArrayList<>() {{
                add("918431726188");
            }};
            String sms = "Dear " + "Ankit Mishra" + ", you got a spin on your transaction of amount " + amount + " from " + name;
            smsServiceHandler.sendSMS(mobiles, sms, NotificationProvider.SMS.GUPSHUP);
        }

        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.OK);
    }
}
