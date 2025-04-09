import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
//        String password = request.getParameter("password");

        ApiClient apiClient = new ApiClient();
        String jsonResponse = null; // this method will call your API

//        try {
////            jsonResponse = apiClient.loginUser(email);
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }

        // You'll likely want to parse the response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);

        if (root.get("success").asBoolean()) {
            long userId = root.get("user").get("id").asLong();
            request.getSession().setAttribute("userId", userId);
            response.sendRedirect("quote.jsp"); // or wherever you want to go
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
