package com.daily.model.entity;

import java.util.Date;

public class PlanDo {
    private Long planId;

    private Integer userId;

    private String content;

    private String type;

    private String frequency;

    private Date startSince;

    private String done;

    private Date lastDoneDate;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency == null ? null : frequency.trim();
    }

    public Date getStartSince() {
        return startSince;
    }

    public void setStartSince(Date startSince) {
        this.startSince = startSince;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done == null ? null : done.trim();
    }

    public Date getLastDoneDate() {
        return lastDoneDate;
    }

    public void setLastDoneDate(Date lastDoneDate) {
        this.lastDoneDate = lastDoneDate;
    }
}