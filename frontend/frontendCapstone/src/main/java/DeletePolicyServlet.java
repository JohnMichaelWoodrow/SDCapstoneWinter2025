import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/deletePolicy")
public class DeletePolicyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String policyId = request.getParameter("policyId");
        String quoteId = request.getParameter("quoteId");

        // Deletes policy
        URL policyUrl = new URL("http://localhost:8080/v1/policy/" + policyId);
        HttpURLConnection policyConn = (HttpURLConnection) policyUrl.openConnection();
        policyConn.setRequestMethod("DELETE");
        policyConn.connect();
        policyConn.getResponseCode();
        policyConn.disconnect();

        // Delete quote which is related to policy
        URL quoteUrl = new URL("http://localhost:8080/v1/quote/" + quoteId);
        HttpURLConnection quoteConn = (HttpURLConnection) quoteUrl.openConnection();
        quoteConn.setRequestMethod("DELETE");
        quoteConn.connect();
        quoteConn.getResponseCode();
        quoteConn.disconnect();

        // Allows this servlet to use the cancel button servlet to return to the quote page
        Long userId = (Long) request.getSession().getAttribute("userId");
        response.sendRedirect("cancelQuote?userId=" + userId);
    }
}
