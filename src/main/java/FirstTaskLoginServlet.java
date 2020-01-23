import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/FirstTaskLoginServlet")
public class FirstTaskLoginServlet extends HttpServlet {
    /*Create method with will be return information about username and password when user
    * writing and send on page FirstForm.html
    * Request - this is what user send to server
    * Response - this is pages what we return user*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*We get from input form where name is usernameFirstField and set to string */
        String username = request.getParameter("usernameFirstField");
        /*We get from input form where name is passwordFirstField and set to string */
        String password = request.getParameter("passwordFirstField");
        /*To send response back to the client, we need to obtain a writer from the response object
        by calling the method getWriter() of the HttpServletResponse interface:*/
        String languages[] = request.getParameterValues("language");
        String languageInfo = "Languages are: ";
        if (languages != null) {
            System.out.println("Languages are: ");
            for (String lang : languages) {
                System.out.println("\t" + lang);
                languageInfo += lang+" ";
            }
        }
        /*Add changes on development branch*/
        System.out.println("username is: " + username);
        System.out.println("password is: " + password);
        PrintWriter printWriter = response.getWriter();
        /*Then use the print() or println() method to deliver the response (in form of HTML code). */
        String htmlRespone = "<html>";
        htmlRespone += "<h2>Your username is: " + username + "<br/>";
        htmlRespone += "Your pass is: "+password+"</h2>";
        htmlRespone += languageInfo;
        htmlRespone += "</html>";
        printWriter.println(htmlRespone);
    }

}
