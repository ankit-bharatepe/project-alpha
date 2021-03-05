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
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class TransactionManage {

    Boolean isJinny = true;

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
        dummyTransaction.setAmount(amount);
        dummyTransaction.setLuckyStatus("false");
        List<DummyTransaction> dummyTransactionList = dummyTransactionDao.getAllTransaction();

        List<String> mobile = new ArrayList<String>() {{
            add("918887943149");
        }};
        String smss = "Dear " + "Ankit Mishra" + ", you received a transaction of " + amount + "from " + name ;
        smsServiceHandler.sendSMS(mobile, smss, NotificationProvider.SMS.GUPSHUP);
        if(dummyTransactionList.size()%3==0){
            if (isJinny){
                isJinny = false;
                List<String> mobiles = new ArrayList<>() {{
                    add("918431726188");
                }};
                String sms = "Dear " + "Ankit Mishra" + ", you got a jinny on your transaction of amount " + amount + " from " + name;
                smsServiceHandler.sendSMS(mobiles, sms, NotificationProvider.SMS.GUPSHUP);
                dummyTransaction.setIsLucky("jin");
                dummyTransaction.setLuckyStatus("true");
            }else {
                isJinny = true;
                List<String> mobiles = new ArrayList<>() {{
                    add("918431726188");
                }};
                String sms = "Dear " + "Ankit Mishra" + ", you got a spin on your transaction of amount " + amount + " from " + name;
                smsServiceHandler.sendSMS(mobiles, sms, NotificationProvider.SMS.GUPSHUP);
                dummyTransaction.setIsLucky("spin");
                dummyTransaction.setLuckyStatus("true");
            }
//            DummyTransaction dummyTransactionZero = new DummyTransaction();
//            dummyTransactionZero.setMerchantId(1L);
//            dummyTransactionZero.setCreditedTo("");
//            dummyTransactionZero.setDebitedFrom("");
//            dummyTransactionZero.setTxnType("");
//            dummyTransactionZero.setStatus("");
//            dummyTransactionZero.setAmount("0.0");
//            dummyTransactionZero.setIsLucky(null);
//            dummyTransactionDao.save(dummyTransactionZero);
        }
        dummyTransactionDao.save(dummyTransaction);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    public ResponseEntity<Object> getJinny() throws JsonProcessingException {
        List<DummyTransaction> transactions =  dummyTransactionDao.getIsLucky();
        Map map = new HashMap();
        map.put("jin" , null);
        map.put("spin" , null);
        for(DummyTransaction transaction : transactions){
            if(!StringUtils.isEmpty(transaction.getLuckyStatus()) && transaction.getLuckyStatus().equalsIgnoreCase("true")){
                map.put(transaction.getIsLucky(),transaction.getLuckyStatus());
                DummyTransaction dummyTransaction = dummyTransactionDao.findById(transaction.getId()).get();
                dummyTransaction.setLuckyStatus("false");
            }
        }
        return new ResponseEntity<>(objectMapper.writeValueAsString(map),HttpStatus.OK);
    }
}
