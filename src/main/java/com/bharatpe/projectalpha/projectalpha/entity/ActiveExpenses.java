package com.bharatpe.projectalpha.projectalpha.entity;

import com.bharatpe.common.entities.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Active_expenses")
public class ActiveExpenses  extends BaseEntity {


    @Column(name="expense_type")
    private String expenseType;

    @Column(name="status")
    private String status;

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
