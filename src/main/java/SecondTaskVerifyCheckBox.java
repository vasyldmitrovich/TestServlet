import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/SecondTaskVerifyCheckBox")
public class SecondTaskVerifyCheckBox extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String languages1[] = request.getParameterValues("language");
        String languageInfo = "Languages are: ";
        if (languages1 != null) {
            System.out.println("Languages are: ");
            for (String lang : languages1) {
                System.out.println("\t" + lang);
                languageInfo += lang+" ";
            }
        }
        String htmlResponse = "<html>"+"<h2>"+languageInfo+"</h2>"+"</html>";
        PrintWriter printWriter = response.getWriter();
        printWriter.write(htmlResponse);

    }
}
