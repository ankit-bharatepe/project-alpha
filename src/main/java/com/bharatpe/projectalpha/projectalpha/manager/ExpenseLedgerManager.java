package com.bharatpe.projectalpha.projectalpha.manager;

import com.bharatpe.projectalpha.projectalpha.dao.ExpenseLedgerDao;
import com.bharatpe.projectalpha.projectalpha.dao.MerchantExpensesAggregatorDao;
import com.bharatpe.projectalpha.projectalpha.entity.ExpenseLedger;
import com.bharatpe.projectalpha.projectalpha.entity.MerchantExpensesAggregator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExpenseLedgerManager {

    Logger logger = LoggerFactory.getLogger(ExpenseManager.class);
    @Autowired
    ExpenseLedgerDao expenseLedgerDao;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MerchantExpensesAggregatorDao merchantExpensesAggregatorDao;

    public ResponseEntity<Object> getExpenseDetail(String merchantId){
        try {
            List<ExpenseLedger> expenseLedgers = expenseLedgerDao.findBYMerchantId(merchantId);
            if (expenseLedgers.size()==0)
                return new ResponseEntity<>("not found",HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(mapper.writeValueAsString(expenseLedgers) , HttpStatus.OK);
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> getDetail(String MerchantId , Long expenseAggId ){
        try {
            List<ExpenseLedger> expenseLedger = expenseLedgerDao.findByMerchantIdAndMerchantExpenseAggId(MerchantId,expenseAggId);
            if (expenseLedger.size()==0)
                return new ResponseEntity<>("not found",HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(mapper.writeValueAsString(expenseLedger),HttpStatus.OK);

        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
