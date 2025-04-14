import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ObjectMapper mapper = new ObjectMapper();
        boolean authenticated = false;
        long userId = 0;
        String name = null;
        String role = null;
        String line;

        // Checks to see if user is an Agent
        URL agentUrl = new URL("http://localhost:8080/v1/agent");
        HttpURLConnection agentConn = (HttpURLConnection) agentUrl.openConnection();
        agentConn.setRequestMethod("GET");

        StringBuilder agentResult = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(agentConn.getInputStream(), "utf-8"))) {
            while ((line = br.readLine()) != null) {
                agentResult.append(line.trim());
            }
        }
        agentConn.disconnect();

        JsonNode agents = mapper.readTree(agentResult.toString());

        for (JsonNode agent : agents) {
            if (agent.get("email").asText().equalsIgnoreCase(email) &&
                    agent.get("password").asText().equals(password)) {

                userId = agent.get("id").asLong();
                name = agent.get("name").asText();
                role = agent.get("user").get("role").asText();
                authenticated = true;
                // If agent, then it breaks
                break;
            }
        }

        // If not agent, it checks customers
        if (!authenticated) {
            URL customerUrl = new URL("http://localhost:8080/v1/customer");
            HttpURLConnection customerConn = (HttpURLConnection) customerUrl.openConnection();
            customerConn.setRequestMethod("GET");

            StringBuilder customerResult = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(customerConn.getInputStream(), "utf-8"))) {
                while ((line = br.readLine()) != null) {
                    customerResult.append(line.trim());
                }
            }
            customerConn.disconnect();

            JsonNode customers = mapper.readTree(customerResult.toString());

            for (JsonNode customer : customers) {
                if (customer.get("email").asText().equalsIgnoreCase(email) &&
                        customer.get("password").asText().equals(password)) {

                    userId = customer.get("id").asLong();
                    name = customer.get("name").asText();
                    role = customer.get("user").get("role").asText();
                    authenticated = true;
                    // If customer, then it breaks
                    break;
                }
            }
        }

        // Sends Agent/Customer to respective logged in page
        if (authenticated) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("name", name);
            session.setAttribute("role", role);

            if ("agent".equalsIgnoreCase(role)) {
                response.sendRedirect("agentDashboard.jsp");
            } else {

                URL customerUrl = new URL("http://localhost:8080/v1/customer/" + userId);
                HttpURLConnection customerConn = (HttpURLConnection) customerUrl.openConnection();
                customerConn.setRequestMethod("GET");
                customerConn.setRequestProperty("Content-Type", "application/json");

                StringBuilder result = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(customerConn.getInputStream(), "utf-8"))) {
                    while ((line = br.readLine()) != null) {
                        result.append(line.trim());
                    }
                }

                customerConn.disconnect();

                // Forward the customer JSON string to the JSP page
                request.setAttribute("policies", result.toString());
                request.getRequestDispatcher("quote.jsp").forward(request, response);

            }
        } else {
            request.setAttribute("error", "Email or password is incorrect.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
