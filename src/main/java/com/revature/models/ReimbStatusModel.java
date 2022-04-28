package com.revature.models;

public class ReimbStatusModel extends Model{

    private int statusId;
    private String status;
    private int reimbId;

    public ReimbStatusModel() {
    }

    public ReimbStatusModel(int statusId, String status, int reimbId) {
        this.statusId = statusId;
        this.status = status;
        this.reimbId = reimbId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    @Override
    public String toString() {
        return statusId + ". Reimbursement = " + reimbId + ", Status = " + status;
    }

}
