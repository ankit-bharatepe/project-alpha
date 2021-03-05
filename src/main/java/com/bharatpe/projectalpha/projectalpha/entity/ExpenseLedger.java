package com.bharatpe.projectalpha.projectalpha.entity;

import com.bharatpe.common.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "expense_ledger")
public class ExpenseLedger  extends BaseEntity {

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private String status;

    @Column(name = "merchant_expense_agg_id")
    private String merchantExpenseAggId;


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMerchantExpenseAggId() {
        return merchantExpenseAggId;
    }

    public void setMerchantExpenseAggId(String merchantExpenseAggId) {
        this.merchantExpenseAggId = merchantExpenseAggId;
    }
}
