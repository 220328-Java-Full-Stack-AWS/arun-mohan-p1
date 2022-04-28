package com.revature.services;

import com.revature.models.ReimbStatusModel;
import com.revature.models.ReimbTypeModel;
import com.revature.persistence.ReimbTypeDAO;

import java.util.LinkedList;
import java.util.List;

public class ReimbTypeService {

    private final ReimbTypeDAO dao;

    public ReimbTypeService() {
        this.dao = new ReimbTypeDAO();
    }

    // implement dao methods
    public ReimbTypeModel create(ReimbTypeModel model) {
        return dao.create(model);
    }

    public ReimbTypeModel read(int id) {
        return dao.read(id);
    }

    public void update(ReimbTypeModel model) {
        dao.update(model);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void delete(ReimbTypeModel model) {
        dao.delete(model);
    }

    public List<ReimbTypeModel> getAll() {
        return dao.getAll();
    }

    // implement user story methods

    /**
     * Filter the reimb type items by type
     * @param brita The type to filter reimb types
     * @return List of all reimb types of that type
     */
    public List<ReimbTypeModel> filterReimbType(String brita) {

        List<ReimbTypeModel> requests = dao.getAll();
        List<ReimbTypeModel> filtered = new LinkedList<>();
        try {
            for (ReimbTypeModel r : requests) {
                if (r.getType().equals(brita)) {
                    filtered.add(r);
                }
            }

        } catch (NullPointerException e) {
            System.out.println("Problem occurred while trying to filter requests. Might be no requests.");
        }

        return filtered;
    }

}
