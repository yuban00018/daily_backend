package com.daily.model.entity.daily;

public class UserPlanRecordDo extends UserPlanRecordDoKey {
    private Integer groupId;

    private String type;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}