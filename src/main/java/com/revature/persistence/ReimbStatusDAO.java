package com.revature.persistence;

import com.revature.ConnectionManager;
import com.revature.models.ReimbStatusModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ReimbStatusDAO implements CRUDInterface<ReimbStatusModel> {

    /**
     * Creates, inserts, and returns the given model into the table
     * @param model The item to be created
     * @return The created item
     */
    @Override
    public ReimbStatusModel create(ReimbStatusModel model) {
        // sql string to parameterize and pass in
        String sql = "INSERT INTO reimb_status (re_status,reimb_id) VALUES (?,?)";
        try {
            // create a connection, pass in sql string, and execute update, returning a result set
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, model.getStatus());
            pstmt.setInt(2, model.getReimbId());
            pstmt.executeUpdate();

            // set the primary key to the automatically generated key (serial)
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int key = keys.getInt(1);
                model.setStatusId(key);
            }

        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to create reimbursement status: " + model);
        }

        return model;
    }

    /**
     * Reads and returns the model matching given id in table
     * @param id The id of the item to be read
     * @return The item read from the table
     */
    @Override
    public ReimbStatusModel read(int id) {
        // create model to be returned
        ReimbStatusModel model = new ReimbStatusModel();
        // same algorithm for each method, string modified for each CRUD function
        String sql = "SELECT * FROM reimb_status WHERE status_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // only parameter is id of row to be read
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            // executeQuery because we want to loop through our selected data in the result set
            while (result.next()) {
                model.setStatusId(result.getInt("status_id"));
                model.setStatus(result.getString("re_status"));
                model.setReimbId(result.getInt("reimb_id"));
            }
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to read reimbursement status ID = " + id);
        }

        return model;
    }

    /**
     * Update an existing model in the table using a given model
     * @param model The item containing the updated information
     */
    @Override
    public void update(ReimbStatusModel model) {

        String sql = "UPDATE reimb_status SET re_status = ?, reimb_id = ? WHERE status_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, model.getStatus());
            pstmt.setInt(2, model.getReimbId());
            pstmt.setInt(3, model.getStatusId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to update reimbursement status: " + model);
        }

    }

    /**
     * Delete a model from the table by its id
     * @param id The item's id
     */
    @Override
    public void delete(int id) {

        String sql = "DELETE FROM reimb_status WHERE status_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // set id and execute
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to delete reimbursement status ID = " + id);
        }

    }

    /**
     * Delete a model from the table by passing in the model
     * @param model The item to be deleted
     */
    @Override
    public void delete(ReimbStatusModel model) {

        int id = model.getStatusId();
        // same as above once we have the id
        String sql = "DELETE FROM reimb_status WHERE status_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to delete reimbursement status ID = " + id);
        }

    }

    /**
     * Return a list of all models in this table
     * @return A LinkedList of all rows
     */
    @Override
    public List<ReimbStatusModel> getAll() {

        // list of models to be returned
        List<ReimbStatusModel> models = new LinkedList<>();
        String sql = "SELECT * FROM reimb_status";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            // execute query so we can loop through data in result set
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                // create a model with all the information read from the table
                ReimbStatusModel model = new ReimbStatusModel();
                model.setStatusId(result.getInt("status_id"));
                model.setStatus(result.getString("re_status"));
                model.setReimbId(result.getInt("reimb_id"));
                // add model to the list
                models.add(model);
            }
        } catch (SQLException e) {
            System.out.println("Problem occurred while trying to list all reimbursement status models.");
        }
        return models;
    }
}
