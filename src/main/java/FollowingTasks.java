import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/FollowingTasks")
public class FollowingTasks extends HttpServlet {
        /*Override method post from clas HttpServlet*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*Create variable and get value from name gender*/
        String gender = request.getParameter("gender");
        System.out.println("Gender is: " + gender);
        String genderIs = "Gender is: " + gender;
        /*Give feedback*/
        String feedback = request.getParameter("feedback");
        System.out.println("Feed back is: " + feedback);
        String feedbackIs ="Feed back is: " + feedback;

        /*Create and show html page*/
        String htmlResponse = "<html>"+"<h2>"+genderIs+"</h2>"+"</html>";
        htmlResponse += "<br/>"+"<h2>"+feedbackIs+"</h2>";
        htmlResponse +="</html>";
        PrintWriter printWriter = response.getWriter();
        printWriter.write(htmlResponse);
    }

}
