package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.UserModel;
import com.revature.services.UserService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// override http methods we want this servlet to handle
public class UserServlet extends HttpServlet {

    private UserService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UserService();
        this.mapper = new ObjectMapper();
    }

    // read
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get request comes in
        // get id out of req header
        //req.getHeader("user_id");

        // get from header user_id, read from dao service and store in model
        UserModel model = service.read(Integer.parseInt(req.getHeader("user_id")));

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

        /*
        switch(req.getHeader("mode")) {
            // use create to add a model to table
            case "register":
                UserDto userDto = mapper.readValue(req.getInputStream(), UserDto.class);
                // create new user
                UserModel newUser = service.create(service.getByUsername(userDto.getUsername()));
                resp.setHeader("access-control-expose-headers", "authToken");
                resp.setHeader("authToken", newUser.getUsername());
                resp.getWriter().print(mapper.writeValueAsString(newUser));
                resp.setStatus(201);    // successful persistence
                break;
            case "login":
                AuthDto authDto = mapper.readValue(req.getInputStream(), AuthDto.class);
                if (service.logIn(authDto.getUsername(), authDto.getPassword())) {
                    resp.setHeader("access-control-expose-headers", "authToken");
                    resp.setHeader("authToken", authDto.getUsername());
                    resp.getWriter().print(authDto.getUsername());
                    resp.setStatus(200);
                }
                else {
                    resp.setStatus(401);
                }
                break;
            default:
                resp.setStatus(400);
                break;
        }*/
        String mode = req.getHeader("mode");
        UserModel model = mapper.readValue(req.getInputStream(), UserModel.class);
        switch (mode) {
            case "login" :
                if (service.logIn(model.getUsername(), model.getPassword())) {
                    resp.getWriter().print(model);
                    resp.setStatus(200);
                }
                else {
                    resp.setStatus(401);
                }
                break;
            case "register" :
                model = service.create(model);
                resp.getWriter().print(model);
                resp.setStatus(201);
                break;
            default:
                resp.setStatus(400);
                break;

        }
        /*
        // read from JSON request body and assign to model
        UserModel model = mapper.readValue(req.getInputStream(), UserModel.class);
        // save model in table
        model = service.create(model);

        // write to response
        String json = mapper.writeValueAsString(model);

        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.setStatus(201);    // we have successfully persisted the model*/

    }

    // update
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // read from JSON request body and assign to model
        UserModel model = mapper.readValue(req.getInputStream(), UserModel.class);
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
        service.delete(Integer.parseInt(req.getHeader("user_id")));
        resp.setStatus(200);



    }
}
