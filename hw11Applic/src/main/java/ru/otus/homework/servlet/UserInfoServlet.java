package ru.otus.homework.servlet;

import ru.otus.homework.configurationForm.ConfigurationFreemaker;
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

        String resultAsString = "<p>UserInfoServlet Get</p>";
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resultAsString);

        Map root= new HashMap();
        List<User> userList = userService.getAll();
        root.put("users", userList);

        ConfigurationFreemaker configurationFreemaker = new ConfigurationFreemaker();
        configurationFreemaker.templateProcessing(root, "getUserTable.ftl", response);

        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String resultAsString = "<p>UserInfoServlet Post</p>";
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        response.setContentType("text/html");

        userService.add(new User(name, age));

        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resultAsString);
        resultAsString = "You add user: " + name + " " + age;
        printWriter.println(resultAsString);

        printWriter.println("<a href='javascript:history.back();'>Back</a>");
        printWriter.flush();
    }
}
