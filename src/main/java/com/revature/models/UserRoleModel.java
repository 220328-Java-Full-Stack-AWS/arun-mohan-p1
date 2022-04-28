package com.revature.models;

public class UserRoleModel extends Model{

    private int roleId;
    private String role;
    private int userId;

    public UserRoleModel() {
    }

    public UserRoleModel(int roleId, String role, int userId) {
        this.roleId = roleId;
        this.role = role;
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return roleId + ". User = " + userId + ", Role = " + role;
    }

}
