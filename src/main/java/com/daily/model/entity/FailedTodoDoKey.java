package com.daily.model.entity;

import java.util.Date;

public class FailedTodoDoKey {
    private Integer userId;

    private Date faileDate;

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
}