package com.bharatpe.projectalpha.projectalpha.entity;

import com.bharatpe.common.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "expense_ledger")
public class ExpenseLedger  extends BaseEntity {

    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private String status;

    @Column(name = "merchant_expense_agg_id")
    private Long merchantExpenseAggId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
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

    public Long getMerchantExpenseAggId() {
        return merchantExpenseAggId;
    }

    public void setMerchantExpenseAggId(Long merchantExpenseAggId) {
        this.merchantExpenseAggId = merchantExpenseAggId;
    }
}
