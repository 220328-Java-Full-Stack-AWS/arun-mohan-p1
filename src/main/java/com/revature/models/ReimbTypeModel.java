package com.revature.models;

public class ReimbTypeModel extends Model{

    private int typeId;
    private String type;
    private int reimbId;

    public ReimbTypeModel() {
    }

    public ReimbTypeModel(int typeId, String type, int reimbId) {
        this.typeId = typeId;
        this.type = type;
        this.reimbId = reimbId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    @Override
    public String toString() {
        return typeId + ". Reimbursement = " + reimbId + ", Type = " + type;
    }


}
