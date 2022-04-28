package com.revature.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ReimbDto {
    private float amount;
    private String description;
    private String subDate;
    private int userId;

    public ReimbDto() {
    }

    public ReimbDto(float amount, String description, String subDate, int userId) {
        this.amount = amount;
        this.description = description;
        this.subDate = subDate;
        this.userId = userId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}