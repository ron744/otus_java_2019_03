import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

public class UserInfo extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String resultAsString = "<p>PublicInfo Page Get</p>";
        response.setContentType("text/html");

        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resultAsString);

        List<User> users = userService.getAll();
        printWriter.println(
                "<table border=\"1\">\n" +
                "    <thead>User list</thead>\n" +
                "<tr>" +
                        "<td>UserName</td>" +
                        "<td>Age</td>" +
                "</tr>");

        for (User user : users){
            printWriter.println(
                    "<tr>" +
                    "<td>" + user.getName() + "</td>" +
                    "<td>" + user.getAge() + "</td>" +
                    "</tr>");
        }
        printWriter.println(

                "</table>-->");
        printWriter.println("<a href='javascript:history.back();'>Back</a>");
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String resultAsString = "<p>PublicInfo Page Post</p>";
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
