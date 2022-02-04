package com.daily.model.entity;

import java.util.Date;

public class FailedTodoDo extends FailedTodoDoKey {
    private Date faileDate;

    private Float rate;

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