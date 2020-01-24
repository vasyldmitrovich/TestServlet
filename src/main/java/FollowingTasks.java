import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/FollowingTasks")
public class FollowingTasks extends HttpServlet {
        /*Override method post from class HttpServlet*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*Create variable and get value from name gender*/
        String gender = "Gender is: " + request.getParameter("gender");
        /*Give feedback*/
        String feedback = "Feed back is: " + request.getParameter("feedback");
        /*Get data from dropBox list*/
        String jobCategory ="Job category is: " + request.getParameter("jobCat");
        /*Read data of file upload field*/


        /*Create and html string*/
        String htmlResponse = "<html>"+"<h2>"+gender+"</h2>"+"</html>";
        htmlResponse += "<br/>"+"<h2>"+feedback+"</h2>";
        htmlResponse += "<br/>"+"<h2>"+jobCategory+"</h2>";

        htmlResponse +="</html>";
        /*Create printWriter because we need to obtain a writer from the response object
        adn send response back to the client*/
        PrintWriter printWriter = response.getWriter();
        printWriter.write(htmlResponse);
    }

}
