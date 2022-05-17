package com.revature.banking.web.servlets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking.models.Account;
import com.revature.banking.services.AccountServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.revature.banking.web.servlets.Authable.checkAuth;

public class AccountServlet extends HttpServlet implements Authable {

    private final AccountServices accountServices;
    private final ObjectMapper mapper;

    public AccountServlet(AccountServices accountServices, ObjectMapper mapper) {
        this.accountServices = accountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null){
            Account account = accountServices.readById(req.getParameter("id"));
            String payload = mapper.writeValueAsString(account);
            resp.getWriter().write(payload);
            return;
        }

        List<Account> accounts = Arrays.asList(accountServices.readAll());
        String payload = mapper.writeValueAsString(accounts);

        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;
        // TODO: Let's create a pokemon
        Account newAccount = mapper.readValue(req.getInputStream(), Account.class); // from JSON to Java Object (Pokemon)
        Account persistedAccount = accountServices.create(newAccount);

        String payload = mapper.writeValueAsString(persistedAccount); // Mapping from Java Object (Account) to JSON

        resp.getWriter().write("Persisted the provided account as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

}