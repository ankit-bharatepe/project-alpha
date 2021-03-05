package com.bharatpe.projectalpha.projectalpha.entity;


import com.bharatpe.common.entities.Merchant;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dummy_transaction")
public class DummyTransaction {
    private Long id;
    private Long merchantId;
    private String status;
    private Long orderId;
    private String txnType;
    private String creditedTo;
    private String debitedFrom;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getCreditedTo() {
        return creditedTo;
    }

    public void setCreditedTo(String creditedTo) {
        this.creditedTo = creditedTo;
    }

    public String getDebitedFrom() {
        return debitedFrom;
    }

    public void setDebitedFrom(String debitedFrom) {
        this.debitedFrom = debitedFrom;
    }
}
