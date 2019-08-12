package ru.otus.homework.servlet;

import ru.otus.homework.configurationForm.TemplateProcessor;
import ru.otus.homework.model.User;
import ru.otus.homework.services.UserServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoServlet extends HttpServlet {

    private UserServiceImpl userService;

    public UserInfoServlet(UserServiceImpl userService){
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();

        Map root= new HashMap();
        List<User> userList = userService.getAll();
        root.put("users", userList);

        TemplateProcessor templateProcessor = new TemplateProcessor();
        templateProcessor.templateProcessing(root, "getUserTable.ftl", response);
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        response.setContentType("text/html");

        userService.add(new User(name, age));
        Map root = new HashMap();
        root.put("user", new User(name, age));

        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();

        TemplateProcessor templateProcessor = new TemplateProcessor();
        templateProcessor.templateProcessing(root, "infoAboutAddUser.ftl", response);
        printWriter.flush();
    }
}
