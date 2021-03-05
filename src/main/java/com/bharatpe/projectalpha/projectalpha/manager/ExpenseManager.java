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
import org.springframework.web.bind.annotation.RequestParam;

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

    public Object getActiveMerchantExpenses(String merchantId){
        Map<String, Object> response = new HashMap<>();

        try{
            List<MerchantExpensesAggregator> merchantExpensesAggregators = (List<MerchantExpensesAggregator>) merchantExpensesAggregatorDao.findByStatusAndMerchantId("ACTIVE", merchantId);

            response.put("success", true);
            response.put("message", "active Expenses List");
            response.put("data", merchantExpensesAggregators);

            return response;
        }catch (Exception ex){
            logger.error("Error Occurred while getting  activeExpensesList Error: ", ex);
        }

        response.put("success", false);
        response.put("message", "SomeThing Went Wrong");
        response.put("data", new ArrayList<MerchantExpensesAggregator>());

        return response;
    }

    public Object addExpenses(String type, Double amount, String bill_id, String provider_name,  String expense_id, String merchant_id, String bill_date, String account_number, String ifsc, String account_name, String school_name, String city, String state, String school_id, String employee_name, String salary, String name, String transaction_title, String other_details, String house_number,String house_address){
        Map<String, Object> response = new HashMap<>();

        try{
            MerchantExpensesAggregator merchantExpensesAggregator = new MerchantExpensesAggregator();
            merchantExpensesAggregator.setMerchantId(merchant_id);
            ActiveExpenses activeExpenses = activeExpensesDao.findById(Long.valueOf(expense_id)).get();
            merchantExpensesAggregator.setActiveExpenseId(activeExpenses.getExpenseType());

            merchantExpensesAggregator.setAmount(amount);

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(bill_date);
            merchantExpensesAggregator.setBillDate(date);
            merchantExpensesAggregator.setStatus("ACTIVE");

            Map<String, Object> data = new HashMap<>();
            if(type.equalsIgnoreCase("BILL")) {
                data.put("bill_id", bill_id);
                data.put("provider_name", provider_name);
                merchantExpensesAggregator.setMode("BILL");

            }else{

                data.put("account_number", account_number);
                data.put("account_name", account_name);
                data.put("ifsc", ifsc);


                if(Objects.nonNull(school_name) && !school_name.isEmpty()){
                    data.put("school_name", school_id);
                }
                if(Objects.nonNull(school_id) && !school_id.isEmpty()){
                    data.put("school_id", school_id);
                }
                if(Objects.nonNull(state) && !state.isEmpty()){
                    data.put("state", state);
                }
                if(Objects.nonNull(city) && !city.isEmpty()){
                    data.put("city", city);
                }
                if(Objects.nonNull(employee_name) && !employee_name.isEmpty()){
                    data.put("employee_name", employee_name);
                }
                if(Objects.nonNull(salary) && !salary.isEmpty()){
                    data.put("salary", salary);
                }
                if(Objects.nonNull(name) && !name.isEmpty()){
                    data.put("name", name);
                }
                if(Objects.nonNull(transaction_title) && !transaction_title.isEmpty()){
                    data.put("transaction_title", transaction_title);
                }
                if(Objects.nonNull(other_details) && !other_details.isEmpty()){
                    data.put("other_details", other_details);
                }
                if(Objects.nonNull(house_number) && !house_number.isEmpty()){
                    data.put("house_number", house_number);
                }
                if(Objects.nonNull(house_address) && !house_address.isEmpty()){
                    data.put("house_address", house_address);
                }

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

    public Object updateExpenses(Long expense_agr_id, Double amount, String bill_id, String provider_name,  String expense_id, String bill_date, String account_number, String ifsc, String account_name, String school_name, String city, String state, String school_id,  String employee_name, String salary, String name, String transaction_title, String other_details, String house_number,String house_address, String status){
        Map<String, Object> response = new HashMap<>();

        try{

            MerchantExpensesAggregator merchantExpensesAggregator = merchantExpensesAggregatorDao.findById(expense_agr_id).get();
            if(Objects.nonNull(amount)){
                merchantExpensesAggregator.setAmount(amount);
            }
            if(Objects.nonNull(bill_id) && !bill_id.isEmpty()) {
                Map<String, Object> data = new HashMap<>();
                data.put("bill_id", bill_id);
                data.put("provider_name", provider_name);
                merchantExpensesAggregator.setMode("BILL");

                merchantExpensesAggregator.setTxnDetails(data.toString());
            }
            if(Objects.nonNull(account_number) && !account_number.isEmpty() && Objects.nonNull(ifsc) && !ifsc.isEmpty()){
                Map<String, Object> data = new HashMap<>();

                data.put("account_number", account_number);
                data.put("ifsc", ifsc);
                data.put("account_name", account_name);

                if(Objects.nonNull(school_id) && !school_id.isEmpty()){
                    data.put("school_id", school_id);
                }
                if(Objects.nonNull(state) && !state.isEmpty()){
                    data.put("state", state);
                }
                if(Objects.nonNull(city) && !city.isEmpty()){
                    data.put("city", city);
                }
                if(Objects.nonNull(school_name) && !school_name.isEmpty()){
                    data.put("school_name", school_id);
                }
                if(Objects.nonNull(employee_name) && !employee_name.isEmpty()){
                    data.put("employee_name", employee_name);
                }
                if(Objects.nonNull(salary) && !salary.isEmpty()){
                    data.put("salary", salary);
                }
                if(Objects.nonNull(name) && !name.isEmpty()){
                    data.put("name", name);
                }
                if(Objects.nonNull(transaction_title) && !transaction_title.isEmpty()){
                    data.put("transaction_title", transaction_title);
                }
                if(Objects.nonNull(other_details) && !other_details.isEmpty()){
                    data.put("other_details", other_details);
                }
                if(Objects.nonNull(house_number) && !house_number.isEmpty()){
                    data.put("house_number", house_number);
                }
                if(Objects.nonNull(house_address) && !house_address.isEmpty()){
                    data.put("house_address", house_address);
                }

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
