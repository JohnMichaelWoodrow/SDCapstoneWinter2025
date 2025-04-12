import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/get-quote")
public class quoteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Collect form data
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        int year = Integer.parseInt(request.getParameter("year"));
        String vin = request.getParameter("vin");

        int driverAge = Integer.parseInt(request.getParameter("driverAge"));
        int accidentCount = Integer.parseInt(request.getParameter("accidentCount"));

        // Gets UserID
        Long customerId = (Long) request.getSession().getAttribute("userId");

        // No UserID gets booted
        if (customerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();

        // Build JSON object
        String vehicleJson = mapper.createObjectNode()
                .put("make", make)
                .put("model", model)
                .put("year", year)
                .put("vin", vin)
                .toString();

        // Send API request to /v1/vehicle (This creates the vehicle)
        URL vehicleUrl = new URL("http://localhost:8080/v1/vehicle");
        HttpURLConnection vehicleConn = (HttpURLConnection) vehicleUrl.openConnection();
        vehicleConn.setRequestMethod("POST");
        vehicleConn.setRequestProperty("Content-Type", "application/json");
        vehicleConn.setDoOutput(true);

        try (OutputStream os = vehicleConn.getOutputStream()) {
            os.write(vehicleJson.getBytes("utf-8"));
        }

        // Get new Vehicle ID
        JsonNode vehicleResponse = mapper.readTree(vehicleConn.getInputStream());
        int vehicleId = vehicleResponse.get("id").asInt();
        vehicleConn.disconnect();

        // Build JSON object x2
        String quoteJson = mapper.createObjectNode()
                .put("customerId", customerId)
                .put("vehicleId", vehicleId)
                .put("driverAge", driverAge)
                .put("accidentCount", accidentCount)
                .put("baseRate", 500.0) // fixed base rate
                .toString();

        // Send API request to /v1/auto_quote (This creates the auto quote)
        URL quoteUrl = new URL("http://localhost:8080/v1/auto_quote");
        HttpURLConnection quoteConn = (HttpURLConnection) quoteUrl.openConnection();
        quoteConn.setRequestMethod("POST");
        quoteConn.setRequestProperty("Content-Type", "application/json");
        quoteConn.setDoOutput(true);

        try (OutputStream os = quoteConn.getOutputStream()) {
            os.write(quoteJson.getBytes("utf-8"));
        }

        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(quoteConn.getInputStream(), "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line.trim());
            }
        }

        quoteConn.disconnect();
        request.setAttribute("quote", result.toString());
        request.getRequestDispatcher("quoteSummary.jsp").forward(request, response);
    }
}
