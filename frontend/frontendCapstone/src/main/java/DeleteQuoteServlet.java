import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/deleteQuote")
public class DeleteQuoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quoteId = request.getParameter("quoteId");

        // Deletes Quote
        URL url = new URL("http://localhost:8080/v1/quote/" + quoteId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        conn.getResponseCode();
        conn.disconnect();

        // Allows this servlet to use the cancel button servlet to return to the quote page
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if ("agent".equalsIgnoreCase(role)) {
            response.sendRedirect("agentDashboard.jsp");
        } else {
            Long userId = (Long) session.getAttribute("userId");
            response.sendRedirect("cancelQuote?userId=" + userId);
        }
    }
}
