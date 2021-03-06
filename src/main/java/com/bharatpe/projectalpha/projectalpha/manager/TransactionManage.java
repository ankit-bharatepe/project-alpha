package com.bharatpe.projectalpha.projectalpha.manager;

import com.bharatpe.common.dao.MerchantBankDetailDao;
import com.bharatpe.common.dao.MerchantDao;
import com.bharatpe.common.entities.Merchant;
import com.bharatpe.common.entities.MerchantBankDetail;
import com.bharatpe.common.enums.NotificationProvider;
import com.bharatpe.common.handlers.SmsServiceHandler;
import com.bharatpe.projectalpha.projectalpha.dao.ActiveCashBackDao;
import com.bharatpe.projectalpha.projectalpha.dao.DummyTransactionDao;
import com.bharatpe.projectalpha.projectalpha.entity.ActiveCashBack;
import com.bharatpe.projectalpha.projectalpha.entity.ActiveExpenses;
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

    @Autowired
    ActiveCashBackDao activeCashBackDao;


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

    public ResponseEntity<Object> getList(){
        try {
            List response = new ArrayList();
            Map<String,String> map1 = new LinkedHashMap<>();
            map1.put("id" , "1");
            map1.put("title" , "2x run");
            map1.put("description" , "having low transactions");
            response.add(map1);


            Map<String,String> map2 = new LinkedHashMap<>();
            map2.put("id" , "2");
            map2.put("title" , "safe gold");
            map2.put("description" , "Merchant having high amount");
            response.add(map2);

            Map<String,String> map3 = new LinkedHashMap<>();
            map3.put("id" , "3");
            map3.put("title" , "Loan");
            map3.put("description" , "never applied for loan");
            response.add(map3);

            Map<String,String> map4 = new LinkedHashMap<>();
            map4.put("id" , "4");
            map4.put("title" , "bharatpe club");
            map4.put("description" , "active user");
            response.add(map4);

            Map<String,String> map5 = new LinkedHashMap<>();
            map5.put("id" , "5");
            map5.put("title" , "interest account cashback");
            map5.put("description" , "not using interest account");
            response.add(map5);

            return new ResponseEntity<>(objectMapper.writeValueAsString(response),HttpStatus.OK);
        }catch (Exception e){

        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> getActiveCashback(String merchantId) throws JsonProcessingException {
        List<ActiveCashBack> activeCashBacks = activeCashBackDao.findByMerchantIdAndStatus(merchantId,"ACTIVE");
        if (activeCashBacks.size()==0)
            return new ResponseEntity<>("Not Found" , HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(objectMapper.writeValueAsString(activeCashBacks),HttpStatus.OK);
    }

    public ResponseEntity<Object> setActiveCashback(String merchantId , String id) throws JsonProcessingException {
        ActiveCashBack activeCashBack = new ActiveCashBack();
        activeCashBack.setStatus("ACTIVE");
        activeCashBack.setActiveCashBackId(id);
        activeCashBack.setMerchantId(merchantId);
        if(id.equalsIgnoreCase("1")){
            activeCashBack.setUrl("");
        }else if(id.equalsIgnoreCase("2")){
            activeCashBack.setUrl("https://safe-gold.bharatpe.in/");
        }else if(id.equalsIgnoreCase("3")){
            activeCashBack.setUrl("https://loan.bharatpe.in/");
        }else if(id.equalsIgnoreCase("4")){
            activeCashBack.setUrl("");
        }else if(id.equalsIgnoreCase("5")){
            activeCashBack.setUrl("https://interest-account.bharatpe.in/");
        }
        activeCashBackDao.save(activeCashBack);
        return new ResponseEntity<>(objectMapper.writeValueAsString("ok"),HttpStatus.OK);
    }

}
