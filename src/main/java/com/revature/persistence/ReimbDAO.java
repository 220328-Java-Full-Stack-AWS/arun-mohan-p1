package com.revature.persistence;

import com.revature.ConnectionManager;
import com.revature.models.ReimbModel;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReimbDAO implements CRUDInterface<ReimbModel> {

    /**
     * Creates, inserts, and returns the given model into the table
     * @param model The item to be created
     * @return The created item
     */
    @Override
    public ReimbModel create(ReimbModel model) {
        // sql string to parameterize and pass in
        String sql = "INSERT INTO reimbs (amount,description,submit_date,user_id) VALUES (?,?,?,?)";
        try {
            // create a connection, pass in sql string, and execute update, returning a result set
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setFloat(1, model.getAmount());
            pstmt.setString(2, model.getDesc());
            pstmt.setString(3, model.getSubDate());
            pstmt.setInt(4, model.getUserId());
            pstmt.executeUpdate();

            // set the primary key to the automatically generated key (serial)
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int key = keys.getInt(1);
                model.setReimbId(key);
            }

        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to create reimbursement: " + model);
        }

        return model;

    }

    /**
     * Reads and returns the model matching given id in table
     * @param id The id of the item to be read
     * @return The item read from the table
     */
    @Override
    public ReimbModel read(int id) {
        // create model to be returned
        ReimbModel model = new ReimbModel();
        // same algorithm for each method, string modified for each CRUD function
        String sql = "SELECT * FROM reimbs WHERE reimb_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // only parameter is id of row to be read
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            // executeQuery because we want to loop through our selected data in the result set
            while (result.next()) {
                model.setReimbId(result.getInt("reimb_id"));
                model.setAmount(result.getFloat("amount"));
                model.setDesc(result.getString("description"));
                model.setSubDate(result.getString("submit_date"));
                model.setUserId(result.getInt("user_id"));
            }
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to read reimbursement ID = " + id);
        }

        return model;
    }

    /**
     * Update an existing model in the table using a given model
     * @param model The item containing the updated information
     */
    @Override
    public void update(ReimbModel model) {

        String sql = "UPDATE reimbs SET amount = ?, description = ?, submit_date = ?, user_id = ? WHERE reimb_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setFloat(1, model.getAmount());
            pstmt.setString(2, model.getDesc());
            pstmt.setString(3, model.getSubDate());
            pstmt.setInt(4, model.getUserId());
            pstmt.setInt(5, model.getReimbId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to update reimbursement type: " + model);
        }

    }

    /**
     * Delete a model from the table by its id
     * @param id The item's id
     */
    @Override
    public void delete(int id) {

        String sql = "DELETE FROM reimbs WHERE reimb_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // set id and execute
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to delete reimbursement ID = " + id);
        }

    }

    /**
     * Delete a model from the table by passing in the model
     * @param model The item to be deleted
     */
    @Override
    public void delete(ReimbModel model) {

        int id = model.getReimbId();
        // same as above once we have the id
        String sql = "DELETE FROM reimbs WHERE reimb_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to delete reimbursement ID = " + id);
        }

    }

    /**
     * Return a list of all models in this table
     * @return A LinkedList of all rows
     */
    @Override
    public List<ReimbModel> getAll() {
        // list of models to be returned
        List<ReimbModel> models = new LinkedList<>();
        String sql = "SELECT * FROM reimbs";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // execute query so we can loop through data in result set
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                // create a model with all the information read from the table
                ReimbModel model = new ReimbModel();
                model.setReimbId(result.getInt("reimb_id"));
                model.setAmount(result.getFloat("amount"));
                model.setDesc(result.getString("description"));
                model.setSubDate(result.getString("submit_date"));
                model.setUserId(result.getInt("user_id"));
                // add model to the list
                models.add(model);
            }
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to list all reimbursement models.");
        }
        return models;
    }
}
