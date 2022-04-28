package com.revature.services;

import com.revature.models.UserRoleModel;
import com.revature.persistence.UserRoleDAO;

import java.util.List;

public class UserRoleService {

    private final UserRoleDAO dao;

    public UserRoleService() {
        this.dao = new UserRoleDAO();
    }

    // implement dao methods
    public UserRoleModel create(UserRoleModel model) {
        return dao.create(model);
    }

    public UserRoleModel read(int id) {
        return dao.read(id);
    }

    public void update(UserRoleModel model) {
        dao.update(model);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void delete(UserRoleModel model) {
        dao.delete(model);
    }

    public List<UserRoleModel> getAll() {
        return dao.getAll();
    }

    // implement user story methods

    /**
     * Return the user role of the given user id
     * @param id The user's id
     * @return The user's role
     */
    public String getRole(int id) {

        // read the row with the given id and return their role
        UserRoleModel model = this.read(id);
        return model.getRole();
    }

    /**
     * Change the role of the given user id to the given role
     * Admin access only
     * @param id The id of the user to change
     * @param role The new role
     */
    public void setRole(int id, String role) {

        // find given user, change the role, and update table
        UserRoleModel model = dao.read(id);
        model.setRole(role);
        dao.update(model);

    }

}
