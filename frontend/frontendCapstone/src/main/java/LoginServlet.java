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
        String jsonResponse = apiClient.getAllUsers(); // TODO currently getting user role cus customer is broken

        System.out.println("Raw API response:");
        System.out.println(jsonResponse);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);

        boolean found = false;
        long userId = 0;

        for (JsonNode user : root) {
            if (user.has("role") && user.get("role").asText().equalsIgnoreCase(email)) { // TODO change role back to email
                found = true;
                userId = user.get("id").asLong();

                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                // TODO set name as session variable to use on quote page and elsewhere

                break;
            }
        }

        if (found) {
            request.getSession().setAttribute("userId", userId);
            response.sendRedirect("quote.jsp");
        } else {
            request.setAttribute("error", "Email not found. Please register.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
