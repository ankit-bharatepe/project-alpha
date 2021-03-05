package com.bharatpe.projectalpha.projectalpha.entity;

import com.bharatpe.common.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "merchant_expenses_aggregator")
public class MerchantExpensesAggregator extends BaseEntity {

    @Column(name="merchant_id")
    private String merchantId;

    @Column(name="active_expense_id")
    private String activeExpenseId;

    @Column(name="amount")
    private Double amount;

    @Column(name="mode")
    private String mode;

    @Column(name="txn_details")
    private String txnDetails;

    @Column(name="status")
    private String status;

    @Column(name="bill_date")
    private Date billDate;


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getActiveExpenseId() {
        return activeExpenseId;
    }

    public void setActiveExpenseId(String activeExpenseId) {
        this.activeExpenseId = activeExpenseId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTxnDetails() {
        return txnDetails;
    }

    public void setTxnDetails(String txnDetails) {
        this.txnDetails = txnDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
}
