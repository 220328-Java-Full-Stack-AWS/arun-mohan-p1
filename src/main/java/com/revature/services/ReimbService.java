package com.revature.services;

import com.revature.models.ReimbModel;
import com.revature.models.ReimbTypeModel;
import com.revature.persistence.ReimbDAO;

import java.util.LinkedList;
import java.util.List;

public class ReimbService {

    private final ReimbDAO dao;

    public ReimbService() {
        this.dao = new ReimbDAO();
    }

    // implement dao methods
    public ReimbModel create(ReimbModel model) {
        return dao.create(model);
    }

    public ReimbModel read(int id) {
        return dao.read(id);
    }

    public void update(ReimbModel model) {
        dao.update(model);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void delete(ReimbModel model) {
        dao.delete(model);
    }

    public List<ReimbModel> getAll() {
        return dao.getAll();
    }

    // implement user story methods


    /**
     * Edit the description of a pending request
     * @param id reimb id to edit
     * @param desc new description string
     */
    public void editDescription(int id, String desc) {

        ReimbModel model = dao.read(id);
        // make sure the new description isn't 500 chars long
        if (desc.length() > 500) {
            desc = desc.substring(0, 500);
        }
        // update the description
        model.setDesc(desc);
        dao.update(model);

    }

    /**
     * Edit the submission date of a pending request
     * @param id reimb id to edit
     * @param date new date string
     */
    public void editDate(int id, String date) {

        ReimbModel model = dao.read(id);
        // update the date
        model.setSubDate(date);
        dao.update(model);

    }

    /**
     * Edit the amount value of a pending request
     * @param id reimb id to edit
     * @param amount new float value for amount
     */
    public void editAmount(int id, float amount) {

        ReimbModel model = dao.read(id);
        // update the description
        model.setAmount(amount);
        dao.update(model);

    }

    /**
     * Return a list of all requests by a given user
     * @param id The user id
     * @return List<ReimbModel> containing all the reimbs submitted by this user
     */
    public List<ReimbModel> getByUser(int id) {
        List<ReimbModel> requests = dao.getAll();
        List<ReimbModel> myRequests = new LinkedList<>();
        for (ReimbModel m : requests) {
            if (m.getUserId() == id) {
                myRequests.add(m);
            }
        }
        return myRequests;
    }

}
