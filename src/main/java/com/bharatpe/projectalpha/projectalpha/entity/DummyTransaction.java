package com.bharatpe.projectalpha.projectalpha.entity;


import com.bharatpe.common.entities.Merchant;

import javax.persistence.*;

@Entity
@Table(name = "dummy_transaction")
public class DummyTransaction {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "status")
    private String status;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "txn_type")
    private String txnType;

    @Column(name = "credited_to")
    private String creditedTo;

    @Column(name = "debited_from")
    private String debitedFrom;

    @Column(name = "is_lucky")
    private String isLucky;

    @Column(name = "amount")
    private String amount;

    @Column(name = "lucky_status")
    private String luckyStatus;

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

    public String getIsLucky() {
        return isLucky;
    }

    public void setIsLucky(String isLucky) {
        this.isLucky = isLucky;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLuckyStatus() {
        return luckyStatus;
    }

    public void setLuckyStatus(String luckyStatus) {
        this.luckyStatus = luckyStatus;
    }
}
