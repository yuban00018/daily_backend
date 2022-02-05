package com.daily.model.entity;

import java.util.Date;

public class FailedTodoDo {
    private Integer id;

    private Integer userId;

    private Date faileDate;

    private Float rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getFaileDate() {
        return faileDate;
    }

    public void setFaileDate(Date faileDate) {
        this.faileDate = faileDate;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}