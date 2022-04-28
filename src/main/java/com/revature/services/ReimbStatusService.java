package com.revature.services;

import com.revature.models.ReimbModel;
import com.revature.models.ReimbStatusModel;
import com.revature.persistence.ReimbStatusDAO;

import java.util.LinkedList;
import java.util.List;

public class ReimbStatusService {

    private final ReimbStatusDAO dao;

    public ReimbStatusService() {
        this.dao = new ReimbStatusDAO();
    }

    // implement dao methods
    public ReimbStatusModel create(ReimbStatusModel model) {
        return dao.create(model);
    }

    public ReimbStatusModel read(int id) {
        return dao.read(id);
    }

    public void update(ReimbStatusModel model) {
        dao.update(model);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void delete(ReimbStatusModel model) {
        dao.delete(model);
    }

    public List<ReimbStatusModel> getAll() {
        return dao.getAll();
    }

    // implement user story methods

    /**
     * Get a list of all requests filtered by status
     * @param brita The status filter can be 'completed', 'denied', 'pending', or 'approved'
     * @return The list of reimbursement status models by chosen status
     */
    public List<ReimbStatusModel> filterReimbStatus(String brita) {

        List<ReimbStatusModel> requests = dao.getAll();
        List<ReimbStatusModel> filtered = new LinkedList<>();
        try {
            switch (brita) {
                // if the filter is all completed requests, add denied and approved to list
                case "completed":
                    for (ReimbStatusModel r : requests) {
                        if (r.getStatus().equals("denied") || r.getStatus().equals("approved")) {
                            filtered.add(r);
                        }
                    }
                    break;
                // else if the filter is status, add all requests of that status
                case "pending":
                case "approved":
                case "denied":
                    for (ReimbStatusModel r : requests) {
                        if (r.getStatus().equals(brita)) {
                            filtered.add(r);
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("Problem occurred while trying to filter requests. Might be no requests.");
        }

        return filtered;
    }

    /**
     * Return a list of a user's reimb statuses filtered by pending or completed
     * Can be improved by using a view that joins reimb and reimb_status tables
     * @param models List of reimb models. Should belong to one user
     * @param brita String filter can be "pending" or "completed"
     * @return
     */
    public List<ReimbModel> filterGivenList(List<ReimbModel> models, String brita) {
        List<ReimbStatusModel> requests = dao.getAll();
        List<ReimbModel> filtered = new LinkedList<>();
        List<Integer> ids = new LinkedList<>();
        List<Integer> filterIds = new LinkedList<>();

        // store all reimb ids
        for (ReimbModel m : models) {
            ids.add(m.getReimbId());

        }
        for (ReimbStatusModel m : requests) {
            // if this reimbursement id is in the id list, filter it
            if (ids.contains(m.getReimbId())) {
                // if filter is pending, add pending. If completed, add approved and denied
                switch (brita) {
                    case "pending" :
                        if (m.getStatus().equals("pending")) {
                            filterIds.add(m.getReimbId());
                        }
                        break;
                    case "completed" :
                        if (m.getStatus().equals("approved") || m.getStatus().equals("denied")) {
                            filterIds.add(m.getReimbId());
                        }
                }
            }
        }
        // add all filtered reimbursements to the list
        for (ReimbModel m : models) {
            if (filterIds.contains(m.getReimbId())) {
                filtered.add(m);
            }
        }

        return filtered;
    }

    /**
     * Change the status of a request to approved
     * @param id The id of the reimbursement status to be approved
     * @return 0 if status was pending, 1 if it was prev the same, 2 if it was prev denied, -1 if it broke
     */
    public int approveRequest(int id) {

        int result;
        ReimbStatusModel model = dao.read(id);
        // might throw exception if model is empty
        try {
            // save the current status to return method result
            switch (model.getStatus()) {
                case "approved":
                    result = 1;
                    break;
                case "denied":
                    result = 2;
                    break;
                default:
                    result = 0;
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("Problem occurred while trying to approve request ID = "
                    + id + ". Might not exist.");
            result = -1;
        }
        // approve the request
        model.setStatus("approved");
        dao.update(model);
        return result;
    }


    /**
     * Change the status of a request to denied
     * @param id The id of the reimbursement status to be denied
     * @return 0 if status was pending, 1 if it was prev the same, 2 if it was prev approved, -1 if it broke
     */
    public int denyRequest(int id) {

        // same algorithm as approve
        int result;
        ReimbStatusModel model = dao.read(id);
        try {
            switch (model.getStatus()) {
                case "denied":
                    result = 1;
                    break;
                case "approved":
                    result = 2;
                    break;
                default:
                    result = 0;
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("Problem occurred while trying to deny request ID = "
                    + id + ". Might not exist.");
            result = -1;
        }
        model.setStatus("denied");
        dao.update(model);
        return result;
    }


}
