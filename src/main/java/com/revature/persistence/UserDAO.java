package com.revature.persistence;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.ConnectionManager;
import com.revature.models.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties
public class UserDAO implements CRUDInterface<UserModel> {

    /**
     * Creates, inserts, and returns the given model into the table
     * @param model The item to be created
     * @return The created item
     */
    @Override
    public UserModel create(UserModel model) {
        // sql string to parameterize and pass in
        // helps prevent SQL Injection
        String sql = "INSERT INTO users (first_name,last_name,user_name,pass_word,email) VALUES (?,?,?,?,?)";
        try {
            // create a connection, pass in sql string, and execute update, returning a result set
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, model.getFirstName());
            pstmt.setString(2, model.getLastName());
            pstmt.setString(3, model.getUsername());
            pstmt.setString(4, model.getPassword());
            pstmt.setString(5, model.getEmail());
            pstmt.executeUpdate();

            // set the primary key to the automatically generated key (serial)
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int key = keys.getInt(1);
                model.setUserId(key);
            }

        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to create user: " + model);
        }

        return model;

    }

    /**
     * Reads and returns the model matching given id in table
     * @param id The id of the item to be read
     * @return The item read from the table
     */
    @Override
    public UserModel read(int id) {
        // create model to be returned
        UserModel model = new UserModel();
        // same algorithm for each method, string modified for each CRUD function
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // only parameter is id of row to be read
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            // executeQuery because we want to loop through our selected data in the result set
            while (result.next()) {
                model.setUserId(result.getInt("user_id"));
                model.setFirstName(result.getString("first_name"));
                model.setLastName(result.getString("last_name"));
                model.setUsername(result.getString("user_name"));
                model.setPassword(result.getString("pass_word"));
                model.setEmail(result.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to read user ID = " + id);
        }

        return model;
    }

    /**
     * Update an existing model in the table using a given model
     * @param model The item containing the updated information
     */
    @Override
    public void update(UserModel model) {

        String sql = "UPDATE users SET first_name = ?, last_name = ?, user_name = ?, pass_word = ?, email = ? WHERE user_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, model.getFirstName());
            pstmt.setString(2, model.getLastName());
            pstmt.setString(3, model.getUsername());
            pstmt.setString(4, model.getPassword());
            pstmt.setString(5, model.getEmail());
            pstmt.setInt(6, model.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to update user: " + model);
        }

    }

    /**
     * Delete a model from the table by its id
     * @param id The item's id
     */
    @Override
    public void delete(int id) {

        String sql = "DELETE FROM users WHERE user_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // set id and execute
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to delete user ID = " + id);
        }

    }

    /**
     * Delete a model from the table by passing in the model
     * @param model The item to be deleted
     */
    @Override
    public void delete(UserModel model) {

        int id = model.getUserId();
        // same as above once we have the id
        String sql = "DELETE FROM users WHERE user_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to delete user ID = " + id);
        }

    }

    /**
     * Return a list of all models in this table
     * @return A LinkedList of all rows
     */
    @Override
    public List<UserModel> getAll() {
        // list of models to be returned
        List<UserModel> models = new LinkedList<>();
        String sql = "SELECT * FROM users";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // execute query so we can loop through data in result set
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                // create a model with all the information read from the table
                UserModel model = new UserModel();
                model.setUserId(result.getInt("user_id"));
                model.setFirstName(result.getString("first_name"));
                model.setLastName(result.getString("last_name"));
                model.setUsername(result.getString("user_name"));
                model.setPassword(result.getString("pass_word"));
                model.setEmail(result.getString("email"));
                // add model to the list
                models.add(model);
            }
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to list all user models.");
        }
        return models;
    }
}
