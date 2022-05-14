package com.daily.model.entity.daily;

public class GroupInfoDo {
    private Integer groupId;

    private Integer allexp;

    private Integer recexp;

    private Integer leaderId;

    private String content;

    private Integer memberNumber;

    private Integer kind;

    private String groupName;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getAllexp() {
        return allexp;
    }

    public void setAllexp(Integer allexp) {
        this.allexp = allexp;
    }

    public Integer getRecexp() {
        return recexp;
    }

    public void setRecexp(Integer recexp) {
        this.recexp = recexp;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(Integer memberNumber) {
        this.memberNumber = memberNumber;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }
}