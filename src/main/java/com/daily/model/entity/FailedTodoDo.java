package com.daily.model.entity;

import java.util.Date;

public class FailedTodoDo extends FailedTodoDoKey {
    private Date faileDate;

    public Date getFaileDate() {
        return faileDate;
    }

    public void setFaileDate(Date faileDate) {
        this.faileDate = faileDate;
    }
}