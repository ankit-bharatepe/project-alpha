package com.bharatpe.projectalpha.projectalpha.schedulers;

import com.bharatpe.common.dao.FPAccountDao;
import com.bharatpe.common.entities.FPAccount;
import com.bharatpe.common.enums.NotificationProvider;
import com.bharatpe.common.handlers.SmsServiceHandler;
import com.bharatpe.projectalpha.projectalpha.dao.ExpenseLedgerDao;
import com.bharatpe.projectalpha.projectalpha.dao.MerchantExpensesAggregatorDao;
import com.bharatpe.projectalpha.projectalpha.entity.ExpenseLedger;
import com.bharatpe.projectalpha.projectalpha.entity.MerchantExpensesAggregator;
import com.bharatpe.projectalpha.projectalpha.manager.ExpenseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class PaymentInitiateScheduler {

    Logger logger = LoggerFactory.getLogger(PaymentInitiateScheduler.class);


    @Autowired
    MerchantExpensesAggregatorDao merchantExpensesAggregatorDao;

    @Autowired
    ExpenseLedgerDao expenseLedgerDao;

    @Autowired
    SmsServiceHandler smsServiceHandler;

    @Autowired
    FPAccountDao fpAccountDao;

 //   @Scheduled(cron = "${payment.complete:-}")
    public void paymentComplete() {

        try {
           Date date = new Date();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Date today = new Date();

            Date todayWithZeroTime = formatter.parse(formatter.format(today));

            logger.info("PaymentCompleteScheduler started ");


            List<ExpenseLedger> expenseLedgers = expenseLedgerDao.findByStatusOrderByAmountAsc("INIT");
            Double totalAmount = 0D;
            for (ExpenseLedger expenseLedger : expenseLedgers) {
                try {
                    String txnId = "BP" + System.currentTimeMillis();
                    FPAccount fpAccount = fpAccountDao.findByMerchant_Id((Long.valueOf(expenseLedger.getMerchantId())));
                    if(fpAccount.getCurrentBalance()  > expenseLedger.getAmount()) {
                        expenseLedger.setStatus("SUCCESS");
                        expenseLedger.setTxnId(txnId);
                        expenseLedgerDao.save(expenseLedger);
                        fpAccount.setCurrentBalance(fpAccount.getCurrentBalance() - expenseLedger.getAmount());
                        fpAccountDao.save(fpAccount);
                        totalAmount += expenseLedger.getAmount();
                    }
                    expenseLedgerDao.save(expenseLedger);
                } catch (Exception ex) {
                    logger.error("Error occurred Er: ", ex);
                }
            }

            if (totalAmount >= 0) {
                List<String> mobiles = new ArrayList<>() {{
                    add("918103565784");
                }};
                String sms = "Dear Ankit Mishra, we have paid your expenses of  " + totalAmount + " please add money to interest account for further convinience";
                smsServiceHandler.sendSMS(mobiles, sms, NotificationProvider.SMS.GUPSHUP);
            }
        }catch (Exception ex){
            logger.error("Error occurred Er: ", ex);
        }
    }

  //  @Scheduled(cron = "${payment.initiate:-}")
    public void paymentInitiate() {

        try {
            Date date = new Date();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Date today = new Date();

            Date todayWithZeroTime = formatter.parse(formatter.format(today));

            logger.info("PaymentInitiateScheduler started ");

            List<MerchantExpensesAggregator> merchantExpensesAggregators = merchantExpensesAggregatorDao.findByStatusAndBillDate("ACTIVE", todayWithZeroTime);
            Double totalAmount = 0D;
            for (MerchantExpensesAggregator merchantExpensesAggregator : merchantExpensesAggregators) {
                try {
                    ExpenseLedger expenseLedger = new ExpenseLedger();
                    String txnId = "BP" + System.currentTimeMillis();

                    if (merchantExpensesAggregator.getMode().equalsIgnoreCase("BILL")) {
                        Random random = new Random();
                        Double amount = random.nextInt(900) + 100.0;
                        expenseLedger.setAmount(amount);
                        totalAmount += amount;
                    } else {
                        expenseLedger.setAmount(merchantExpensesAggregator.getAmount());
                        totalAmount += merchantExpensesAggregator.getAmount();
                    }
                    expenseLedger.setTxnId(txnId);
                    expenseLedger.setMerchantExpenseAggId(merchantExpensesAggregator.getId().toString());
                    expenseLedger.setStatus("INIT");
                    expenseLedger.setMerchantId(merchantExpensesAggregator.getMerchantId());

                    expenseLedgerDao.save(expenseLedger);
                } catch (Exception ex) {
                    logger.error("Error occurred Er: ", ex);
                }
            }

            if (totalAmount >= 0) {
                List<String> mobiles = new ArrayList<>() {{
                    add("918103565784");
                }};
                String sms = "Dear Ankit Mishra, your total expenses of  " + totalAmount + " will be paid by today ";
                smsServiceHandler.sendSMS(mobiles, sms, NotificationProvider.SMS.GUPSHUP);
            }
        }catch (Exception ex){
            logger.error("Error occurred Er: ", ex);
        }
    }
}
