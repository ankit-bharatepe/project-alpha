package com.bharatpe.projectalpha.projectalpha.manager;

import com.bharatpe.projectalpha.projectalpha.dao.ActiveExpensesDao;
import com.bharatpe.projectalpha.projectalpha.dao.MerchantExpensesAggregatorDao;
import com.bharatpe.projectalpha.projectalpha.entity.ActiveExpenses;
import com.bharatpe.projectalpha.projectalpha.entity.MerchantExpensesAggregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ExpenseManager {

    @Autowired
    ActiveExpensesDao activeExpensesDao;

    @Autowired
    MerchantExpensesAggregatorDao merchantExpensesAggregatorDao;

    Logger logger = LoggerFactory.getLogger(ExpenseManager.class);

    public Object getActiveExpenses(){
        Map<String, Object> response = new HashMap<>();

        try{
            List<ActiveExpenses> activeExpensesList = (List<ActiveExpenses>) activeExpensesDao.findAll();

            response.put("success", true);
            response.put("message", "active Expenses List");
            response.put("data", activeExpensesList);

            return response;
        }catch (Exception ex){
            logger.error("Error Occurred while getting  activeExpensesList Error: ", ex);
        }

        response.put("success", false);
        response.put("message", "SomeThing Went Wrong");
        response.put("data", new ArrayList<ActiveExpenses>());

        return response;
    }

    public Object addExpenses(String type, Double amount, String bill_id,  String expense_id, String merchant_id, String bill_date, String account_number, String ifsc){
        Map<String, Object> response = new HashMap<>();

        try{
            MerchantExpensesAggregator merchantExpensesAggregator = new MerchantExpensesAggregator();
            merchantExpensesAggregator.setMerchantId(merchant_id);
            merchantExpensesAggregator.setActiveExpenseId(expense_id);
            merchantExpensesAggregator.setAmount(amount);

            DateFormat format = new SimpleDateFormat("YYYY-MM-DD");
            Date date = format.parse(bill_date);
            merchantExpensesAggregator.setBillDate(date);
            merchantExpensesAggregator.setStatus("ACTIVE");

            Map<String, Object> data = new HashMap<>();
            if(type.equalsIgnoreCase("BILL")) {
                data.put("bill_id", bill_id);
                merchantExpensesAggregator.setMode("BILL");

            }else{
                data.put("account_number", account_number);
                data.put("ifsc", ifsc);

                merchantExpensesAggregator.setMode("REGULAR");
            }
            merchantExpensesAggregator.setTxnDetails(data.toString());


            merchantExpensesAggregator = merchantExpensesAggregatorDao.save(merchantExpensesAggregator);

            response.put("success", true);
            response.put("message", "active Expenses List");
            response.put("data", merchantExpensesAggregator);

            return response;
        }catch (Exception ex){
            logger.error("Error Occurred while getting  activeExpensesList Error: ", ex);
        }

        response.put("success", false);
        response.put("message", "SomeThing Went Wrong");

        return response;
    }

    public Object updateExpenses(Long expense_agr_id, Double amount, String bill_id,  String expense_id, String bill_date, String account_number, String ifsc, String status){
        Map<String, Object> response = new HashMap<>();

        try{
            MerchantExpensesAggregator merchantExpensesAggregator = merchantExpensesAggregatorDao.findById(expense_agr_id).get();
            if(Objects.nonNull(amount)){
                merchantExpensesAggregator.setAmount(amount);
            }
            if(Objects.nonNull(bill_id) && !bill_id.isEmpty()) {
                Map<String, Object> data = new HashMap<>();
                data.put("bill_id", bill_id);
                merchantExpensesAggregator.setMode("BILL");

                merchantExpensesAggregator.setTxnDetails(data.toString());
            }
            if(Objects.nonNull(account_number) && !account_number.isEmpty() && Objects.nonNull(ifsc) && !ifsc.isEmpty()){
                Map<String, Object> data = new HashMap<>();

                data.put("account_number", account_number);
                data.put("ifsc", ifsc);

                merchantExpensesAggregator.setMode("REGULAR");
                merchantExpensesAggregator.setTxnDetails(data.toString());
            }
            if(Objects.nonNull(bill_date) && !bill_date.isEmpty()){
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(bill_date);

                merchantExpensesAggregator.setBillDate(date);
            }
            if(Objects.nonNull(expense_id) && !expense_id.isEmpty()){
                merchantExpensesAggregator.setActiveExpenseId(expense_id);
            }
            if(Objects.nonNull(status) && !status.isEmpty()){
                merchantExpensesAggregator.setActiveExpenseId(expense_id);
            }


            merchantExpensesAggregator = merchantExpensesAggregatorDao.save(merchantExpensesAggregator);

            response.put("success", true);
            response.put("message", "active Expenses List");
            response.put("data", merchantExpensesAggregator);

            return response;
        }catch (Exception ex){
            logger.error("Error Occurred while getting  activeExpensesList Error: ", ex);
        }

        response.put("success", false);
        response.put("message", "SomeThing Went Wrong");

        return response;
    }
}
