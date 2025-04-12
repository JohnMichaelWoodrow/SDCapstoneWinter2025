import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Looking for: " + email);

        ApiClient apiClient = new ApiClient();
        String jsonResponse = apiClient.getAllCustomers();

        System.out.println("Raw API response:");
        System.out.println(jsonResponse);

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

                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("name", username);

                break;
            }
        }

        if (found) {
            request.getSession().setAttribute("userId", userId);
            response.sendRedirect("quote.jsp");
        } else {
            request.setAttribute("error", "Email or password is incorrect.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
