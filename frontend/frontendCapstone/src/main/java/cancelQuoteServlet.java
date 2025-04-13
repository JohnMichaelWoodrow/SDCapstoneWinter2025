import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@WebServlet("/cancelQuote")
public class cancelQuoteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");

        // Get customer data from API
        URL customerUrl = new URL("http://localhost:8080/v1/customer/" + userId);
        HttpURLConnection customerConn = (HttpURLConnection) customerUrl.openConnection();
        customerConn.setRequestMethod("GET");
        customerConn.setRequestProperty("Content-Type", "application/json");

        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(customerConn.getInputStream(), "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line.trim());
            }
        }

        customerConn.disconnect();

        // Forward the customer JSON string to the JSP page
        request.setAttribute("policies", result.toString());
        request.getRequestDispatcher("quote.jsp").forward(request, response);
    }
}
