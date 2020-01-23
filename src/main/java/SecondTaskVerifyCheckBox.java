import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//TODO do not working that
@WebServlet("/SecondTaskVerifyCheckBox")
public class SecondTaskVerifyCheckBox extends HttpServlet {

    protected void verifyCheckBox(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String languages[] = request.getParameterValues("language");

        if (languages != null) {
            System.out.println("Languages are: ");
            for (String lang : languages) {
                System.out.println("\t" + lang);
            }
        }
        PrintWriter printWriter = response.getWriter();

    }
}
