package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ReimbStatusModel;
import com.revature.models.ReimbTypeModel;
import com.revature.services.ReimbTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbTypeServlet extends HttpServlet {

    private ReimbTypeService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new ReimbTypeService();
        this.mapper = new ObjectMapper();
    }

    // read
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get reimb by type id
        /*
        // get request comes in
        // get id out of req header

        ReimbTypeModel model = service.read(Integer.parseInt(req.getHeader("type_id")));

        // turn model into a string to write to json
        String json = mapper.writeValueAsString(model);

        // tell client response is of certain type text/html, application/json, etc.
        resp.setContentType("application/json");

        // get request query parameters (key = value pairs in URI)
        // req.getParameter("item_id");

        // print model to response
        resp.getWriter().print(json);
        resp.setStatus(200);*/

        // get by reimb type
        String filter = req.getHeader("filter");
        List<ReimbTypeModel> models = service.filterReimbType(filter);
        String json = mapper.writeValueAsString(models);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    // create
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // read from JSON request body and assign to model
        ReimbTypeModel model = mapper.readValue(req.getInputStream(), ReimbTypeModel.class);
        // save model in table
        model = service.create(model);

        // write to response
        String json = mapper.writeValueAsString(model);

        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.setStatus(201);    // we have successfully persisted the model

    }

    // update
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // read from JSON request body and assign to model
        ReimbTypeModel model = mapper.readValue(req.getInputStream(), ReimbTypeModel.class);
        // update model in table
        service.update(model);

        // write to response
        String json = mapper.writeValueAsString(model);

        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.setStatus(201);    // we have successfully persisted the model



    }

    // delete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // delete the item with the given id in the req header
        service.delete(Integer.parseInt(req.getHeader("type_id")));
        resp.setStatus(200);



    }
}

