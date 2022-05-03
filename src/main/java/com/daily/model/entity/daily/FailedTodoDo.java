package com.daily.model.entity.daily;

import java.util.Date;

public class FailedTodoDo {
    private Long failedId;

    private Integer userId;

    private Date faileDate;

    private Float rate;

    public Long getFailedId() {
        return failedId;
    }

    public void setFailedId(Long failedId) {
        this.failedId = failedId;
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