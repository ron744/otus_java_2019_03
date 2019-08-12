package ru.otus.homework.servlet;

import ru.otus.homework.configurationForm.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PrivateInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        response.setContentType("text/html");

        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();

        Map root= new HashMap();
        root.put("", "");

        TemplateProcessor templateProcessor = new TemplateProcessor();
        templateProcessor.templateProcessing(root, "addUser.ftl", response);
        printWriter.flush();
    }
}
