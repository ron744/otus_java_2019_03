import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PrivateInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String resultAsString = "<p>PublicInfo Page Get</p>";
        response.setContentType("text/html");

        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resultAsString);
        printWriter.println("<!DOCTYPE html>" +
                "<html>" +
                "<head></head>" +
                "<body>" +
                "<form method=\"post\" action=\"userInfo\">" +
                "Name<input type=\"text\" name=\"name\">" +
                "Age<input type=\"number\" name=\"age\">" +
                "<input type=\"submit\" value=\"add user to dataBase\">" +
                "</form>" +
                "<form method=\"get\" action=\"userInfo\">" +
                "<input type=\"submit\" value=\"GetUserList\">" +
                "</form>" +
                "</body>" +
                "</html>");

        printWriter.println("<a href='javascript:history.back();'>Back</a>");
        printWriter.flush();
    }
}
