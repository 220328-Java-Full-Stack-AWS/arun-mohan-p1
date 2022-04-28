package com.revature.persistence;

import com.revature.ConnectionManager;
import com.revature.models.UserRoleModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserRoleDAO implements CRUDInterface<UserRoleModel>{

    /**
     * Creates, inserts, and returns the given model into the table
     * @param model The item to be created
     * @return The created item
     */
    @Override
    public UserRoleModel create(UserRoleModel model) {
        // sql string to parameterize and pass in
        String sql = "INSERT INTO user_role (role_type,user_id) VALUES (?,?)";
        try {
            // create a connection, pass in sql string, and execute update, returning a result set
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, model.getRole());
            pstmt.setInt(2, model.getUserId());
            pstmt.executeUpdate();

            // set the primary key to the automatically generated key (serial)
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int key = keys.getInt(1);
                model.setRoleId(key);
            }

        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to create user role: " + model);
        }

        return model;
    }

    /**
     * Reads and returns the model matching given id in table
     * @param id The id of the item to be read
     * @return The item read from the table
     */
    @Override
    public UserRoleModel read(int id) {
        // create model to be returned
        UserRoleModel model = new UserRoleModel();
        // same algorithm for each method, string modified for each CRUD function
        String sql = "SELECT * FROM user_role WHERE role_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // only parameter is id of row to be read
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            // executeQuery because we want to loop through our selected data in the result set
            while (result.next()) {
                model.setRoleId(result.getInt("role_id"));
                model.setRole(result.getString("role_type"));
                model.setUserId(result.getInt("user_id"));
            }
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to read user role ID = " + id);
        }

        return model;
    }

    /**
     * Update an existing model in the table using a given model
     * @param model The item containing the updated information
     */
    @Override
    public void update(UserRoleModel model) {

        String sql = "UPDATE user_role SET role_type = ?, user_id = ? WHERE role_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, model.getRole());
            pstmt.setInt(2, model.getUserId());
            pstmt.setInt(3, model.getRoleId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to update user role: " + model);
        }

    }

    /**
     * Delete a model from the table by its id
     * @param id The item's id
     */
    @Override
    public void delete(int id) {

        String sql = "DELETE FROM user_role WHERE role_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // set id and execute
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to delete user role ID = " + id);
        }

    }

    /**
     * Delete a model from the table by passing in the model
     * @param model The item to be deleted
     */
    @Override
    public void delete(UserRoleModel model) {

        int id = model.getRoleId();
        // same as above once we have the id
        String sql = "DELETE FROM user_role WHERE role_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to delete user role ID = " + id);
        }

    }

    /**
     * Return a list of all models in this table
     * @return A LinkedList of all rows
     */
    @Override
    public List<UserRoleModel> getAll() {

        // list of models to be returned
        List<UserRoleModel> models = new LinkedList<>();
        String sql = "SELECT * FROM user_role";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // execute query so we can loop through data in result set
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                // create a model with all the information read from the table
                UserRoleModel model = new UserRoleModel();
                model.setRoleId(result.getInt("role_id"));
                model.setRole(result.getString("role_type"));
                model.setUserId(result.getInt("user_id"));
                // add model to the list
                models.add(model);
            }
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to list all user role models.");
        }
        return models;
    }
}
