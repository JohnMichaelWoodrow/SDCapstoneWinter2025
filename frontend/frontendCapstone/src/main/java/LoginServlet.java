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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ApiClient apiClient = new ApiClient();
        String jsonResponse = apiClient.getAllCustomers();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);

        boolean found = false;
        long userId = 0;
        String username = null;

        for (JsonNode user : root) {
            if (user.has("email") && user.has("password") &&
                    user.get("email").asText().equalsIgnoreCase(email) &&
                    user.get("password").asText().equals(password)) {
                found = true;
                userId = user.get("id").asLong();
                username = user.get("name").toString();

                JsonNode userNode = user.get("user");
                String userRole = userNode.get("role").asText();

                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("name", username);
                session.setAttribute("role", userRole);

                break;
            }
        }

        if (found) {
            // Set session variables
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("name", username);

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
        } else {
            request.setAttribute("error", "Email or password is incorrect.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
