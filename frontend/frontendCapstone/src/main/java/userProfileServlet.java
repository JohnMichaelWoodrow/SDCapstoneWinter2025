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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@WebServlet("/userProfile")
public class userProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");

        HttpSession session = request.getSession();
        String userRole = (String) session.getAttribute("role");

        if (Objects.equals(userRole, "customer")) {
            URL customerUrl = new URL("http://localhost:8080/v1/customer/" + userId);
            HttpURLConnection customerConn = (HttpURLConnection) customerUrl.openConnection();
            customerConn.setRequestMethod("GET");
            customerConn.setRequestProperty("Content-Type", "application/json");

            StringBuilder quoteJson = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(customerConn.getInputStream(), "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    quoteJson.append(line.trim());
                }
            }

            customerConn.disconnect();
            request.setAttribute("customer", quoteJson.toString());

        } else if (Objects.equals(userRole, "agent")) {
            URL agentUrl = new URL("http://localhost:8080/v1/agent/" + userId);
            HttpURLConnection agentConn = (HttpURLConnection) agentUrl.openConnection();
            agentConn.setRequestMethod("GET");
            agentConn.setRequestProperty("Content-Type", "application/json");

            StringBuilder quoteJson = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(agentConn.getInputStream(), "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    quoteJson.append(line.trim());
                }
            }

            agentConn.disconnect();
            request.setAttribute("customer", quoteJson.toString());
        }

        request.getRequestDispatcher("userProfile.jsp").forward(request, response);
    }
}
