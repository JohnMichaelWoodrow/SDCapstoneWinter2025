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
import java.util.Objects;

@WebServlet("/updateUserProfile")
public class updateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String newName = request.getParameter("newName");
        String newEmail = request.getParameter("newEmail");
        String newPassword = request.getParameter("newPassword");
        String currentName = request.getParameter("currentName");
        String currentEmail = request.getParameter("currentEmail");
        String currentPassword = request.getParameter("currentPassword");
        String userId = request.getParameter("userId");

        HttpSession session = request.getSession();
        String userRole = (String) session.getAttribute("role");

        String updateName = "";
        String updateEmail = "";
        String updatePassword = "";

        if (!Objects.equals(newName, "")) {
            updateName = newName;
        } else {
            updateName = currentName;
        }

        if (!Objects.equals(newEmail, "")) {
            updateEmail = newEmail;
        } else {
            updateEmail = currentEmail;
        }

        if (!Objects.equals(newPassword, "")) {
            updatePassword = newPassword;
        } else {
            updatePassword = currentPassword;
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.createObjectNode()
                .put("name", updateName)
                .put("email", updateEmail)
                .put("password", updatePassword)
                .toString();

        System.out.println(jsonString);

        int status = 0;

        if (Objects.equals(userRole, "customer")) {
            URL customerUrl = new URL("http://localhost:8080/v1/customer/" + userId);
            HttpURLConnection customerConn = (HttpURLConnection) customerUrl.openConnection();
            customerConn.setRequestMethod("PUT");
            customerConn.setRequestProperty("Content-Type", "application/json");
            customerConn.setDoOutput(true);

            try (OutputStream os = customerConn.getOutputStream()) {
                os.write(jsonString.getBytes("utf-8"));
            }

            status = customerConn.getResponseCode();
            customerConn.disconnect();

        } else if (Objects.equals(userRole, "agent")) {
            URL agentUrl = new URL("http://localhost:8080/v1/agent/" + userId);
            HttpURLConnection agentConn = (HttpURLConnection) agentUrl.openConnection();
            agentConn.setRequestMethod("PUT");
            agentConn.setRequestProperty("Content-Type", "application/json");
            agentConn.setDoOutput(true);

            try (OutputStream os = agentConn.getOutputStream()) {
                os.write(jsonString.getBytes("utf-8"));
            }

            status = agentConn.getResponseCode();
            agentConn.disconnect();
        }


        if (status == 200) {
            System.out.println("Success");
            session.setAttribute("name", updateName);
        } else {
            System.out.println("Failed");
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}
