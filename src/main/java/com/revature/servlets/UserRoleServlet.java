package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.UserRoleModel;
import com.revature.services.UserRoleService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// override http methods we want this servlet to handle
public class UserRoleServlet extends HttpServlet {

    private UserRoleService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UserRoleService();
        this.mapper = new ObjectMapper();
    }

    // read
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get request comes in
        // get id out of req header
        //req.getHeader("user_id");

        // get from header user_id, read from dao service and store in model
        UserRoleModel model = service.read(Integer.parseInt(req.getHeader("role_id")));

        // turn model into a string to write to json
        String json = mapper.writeValueAsString(model);

        // tell client response is of certain type text/html, application/json, etc.
        resp.setContentType("application/json");

        // get request query parameters (key = value pairs in URI)
        // req.getParameter("item_id");

        // print model to response
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    // create
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // read from JSON request body and assign to model
        UserRoleModel model = mapper.readValue(req.getInputStream(), UserRoleModel.class);
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
        UserRoleModel model = mapper.readValue(req.getInputStream(), UserRoleModel.class);
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
        service.delete(Integer.parseInt(req.getHeader("role_id")));
        resp.setStatus(200);



    }
}
