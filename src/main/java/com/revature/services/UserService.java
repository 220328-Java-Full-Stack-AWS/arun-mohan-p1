package com.revature.services;

import com.revature.models.UserModel;
import com.revature.persistence.UserDAO;

import java.sql.SQLException;
import java.util.List;

// service classes are used to abstract DAOs away and create a clean interface to implement CRUD
public class UserService {

    private final UserDAO dao;

    public UserService() {
        this.dao = new UserDAO();
    }

    // implement dao methods
    public UserModel create(UserModel model) {
        return dao.create(model);
    }

    public UserModel read(int id) {
        return dao.read(id);
    }

    public void update(UserModel model) {
        dao.update(model);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void delete(UserModel model) {
        dao.delete(model);
    }

    public List<UserModel> getAll() {
        return dao.getAll();
    }

    // implement user story methods


    /**
     * Register a new user and add them to the user table
     * @param model New user to be registered
     * @return The new user
     */
    public UserModel register(UserModel model) {

        // if username exists in table, do not register
        for (UserModel u : dao.getAll()) {
            if (u.getUsername().equals(model.getUsername())) {
                System.out.println("Registration incomplete. Username might be taken.");
                return new UserModel();
            }
        }
        // create and return new user model, user_id will be replaced by primary key
        return dao.create(model);

    }

    /**
     * Determine if login is successful if username and password match an existing model
     * @param username Existing username
     * @param pass Existing password
     * @return True if login information exists in table, false otherwise
     */
    public boolean logIn(String username, String pass) {

        for (UserModel u : dao.getAll()) {
            // if username and password match, return true
            if (u.getUsername().equals(username) && u.getPassword().equals(pass)) {
                System.out.println("login successful");
                return true;
            }
            // else if the username matched but password didn't try again
            else if (u.getUsername().equals(username) && !(u.getPassword().equals(pass))) {
                System.out.println("Password is incorrect. Please try again.");
                return false;
            }
        }
        // if we made it here, username was not found
        System.out.println("Account not found, please try again or register for a new one.");
        return false;

    }

}
