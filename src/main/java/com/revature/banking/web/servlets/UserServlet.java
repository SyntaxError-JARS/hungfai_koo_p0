package com.revature.banking.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking.exceptions.ResourcePersistanceException;
import com.revature.banking.models.Account;
import com.revature.banking.models.User;
import com.revature.banking.services.UserServices;
import com.revature.banking.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.revature.banking.web.servlets.Authable.checkAuth;


public class UserServlet extends HttpServlet implements Authable {

    private final UserServices userServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public UserServlet(UserServices userServices, ObjectMapper mapper) {
        this.userServices = userServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;
        // The below code allows to split information from the endpoint after the /trainers/. Reminder the first element is empty because it takes the value from before the first /
//        String pathInfo = req.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts[0] + pathParts[1] + pathParts[2]);


        // Handling the query params in the /users?id=x&email=y
        if(req.getParameter("id") != null && req.getParameter("email") != null){
            resp.getWriter().write("Hey you have the follow id and email " + req.getParameter("id") + " " + req.getParameter("email") );
            return;
        }

        // Handling the query params in the endpoint /users?id=x
        if(req.getParameter("id") != null){
            User user;
            try {
                user = userServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e){
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(user);
            resp.getWriter().write(payload);
            return;
        }

        List<User> users = Arrays.asList(userServices.readAll());
        String payload = mapper.writeValueAsString(users);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: Let's create a User
        User newUser = mapper.readValue(req.getInputStream(), User.class); // from JSON to Java Object (User)
        User persistedUser = userServices.create(newUser);

        String payload = mapper.writeValueAsString(persistedUser); // Mapping from Java Object (User) to JSON

        resp.getWriter().write("Persisted the provided user as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}