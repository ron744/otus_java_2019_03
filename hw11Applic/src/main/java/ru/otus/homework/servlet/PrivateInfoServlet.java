package ru.otus.homework.servlet;

import ru.otus.homework.configurationForm.ConfigurationFreemaker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PrivateInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String resultAsString = "<p>SetUser</p>";
        response.setContentType("text/html");

        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resultAsString);

        Map root= new HashMap();
        root.put("", "");

        ConfigurationFreemaker configurationFreemaker = new ConfigurationFreemaker();
        configurationFreemaker.templateProcessing(root, "setUser.tfl", response);

        printWriter.println("<a href='javascript:history.back();'>Back</a>");
        printWriter.flush();
    }
}
