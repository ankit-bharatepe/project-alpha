package com.bharatpe.projectalpha.projectalpha.entity;

import com.bharatpe.common.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Active_cashback")
public class ActiveCashBack extends BaseEntity {
    @Column(name="merchant_id")
    private String merchantId;

    @Column(name="active_cashback_id")
    private String activeCashBackId;

    @Column(name = "status")
    private String status;

    @Column(name = "url")
    private String url;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getActiveCashBackId() {
        return activeCashBackId;
    }

    public void setActiveCashBackId(String activeCashBackId) {
        this.activeCashBackId = activeCashBackId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
