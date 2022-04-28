package com.revature.models;

public class ReimbModel extends Model{

    private int reimbId;
    private float amount;
    private String desc;
    private String subDate;
    private int userId;

    public ReimbModel() {
    }

    public ReimbModel(int reimbId, float amount, String desc, String subDate, int userId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.desc = desc;
        this.subDate = subDate;
        this.userId = userId;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSubDate() {
        return subDate;
    }

    public void setSubDate(String subDate) {
        this.subDate = subDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return reimbId + ". \"" + desc + "\", submit = " + subDate + ", user = " + userId;
    }

}
