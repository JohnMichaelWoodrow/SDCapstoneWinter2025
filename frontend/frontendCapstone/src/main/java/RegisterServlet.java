import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Collect form data
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("regemail");
        String password = request.getParameter("regpassword");
        String confirmPassword = request.getParameter("confpassword");

        // Checks if fields are empty, if so it throws an error
        if (firstName == null || lastName == null || email == null || password == null || confirmPassword == null ||
                firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            request.setAttribute("registerError", "Registration failed. All fields are required.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Checks if passwords match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("registerError", "Passwords do not match.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String fullName = firstName + " " + lastName;

        // Build JSON object
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.createObjectNode()
                .put("name", fullName)
                .put("email", email)
                .put("password", password)
                .toString();

        // Send API request
        URL url = new URL("http://localhost:8080/v1/customer");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonString.getBytes("utf-8"));
        }

        int status = conn.getResponseCode();
        conn.disconnect();

        if (status == 200) {
            request.setAttribute("registerSuccess", "Registration successful.");
        } else {
            request.setAttribute("registerError", "Registration failed. Email already taken.");
        }

        // Keeps user on login page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}