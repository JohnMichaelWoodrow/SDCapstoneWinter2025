import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/viewCustomer")
public class ViewCustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String customerId = request.getParameter("customerId");

        if (customerId == null || customerId.isEmpty()) {
            request.setAttribute("error", "Customer ID is required.");
            request.getRequestDispatcher("agentDashboard.jsp").forward(request, response);
            return;
        }

        URL url = new URL("http://localhost:8080/v1/customer/" + customerId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        StringBuilder jsonResponse = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonResponse.append(line.trim());
            }
        }
        conn.disconnect();

        request.setAttribute("policies", jsonResponse.toString());
        request.setAttribute("customerId", customerId);
        request.getRequestDispatcher("viewCustomer.jsp").forward(request, response);
    }
}
