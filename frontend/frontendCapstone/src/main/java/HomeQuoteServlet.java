import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/get-home-quote")
public class HomeQuoteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Collect form data
        String address = request.getParameter("address");
        int yearBuilt = Integer.parseInt(request.getParameter("yearBuilt"));
        double homeValue = Double.parseDouble(request.getParameter("homeValue"));
        String typeOfDwelling = request.getParameter("typeOfDwelling");
        String heatingType = request.getParameter("heatingType");
        String location = request.getParameter("location");
        double liabilityLimit = Double.parseDouble(request.getParameter("liabilityLimit"));

        // Gets UserID
        Long customerId = (Long) request.getSession().getAttribute("userId");

        // No UserID gets booted
        if (customerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();

        // Build JSON object
        String homeJson = mapper.createObjectNode()
                .put("address", address)
                .put("yearBuilt", yearBuilt)
                .put("homeValue", homeValue)
                .put("typeOfDwelling", typeOfDwelling)
                .put("heatingType", heatingType)
                .put("location", location)
                .put("liabilityLimit", liabilityLimit)
                .toString();

        // Send API request to /v1/home (This creates the home)
        URL homeUrl = new URL("http://localhost:8080/v1/home");
        HttpURLConnection homeConn = (HttpURLConnection) homeUrl.openConnection();
        homeConn.setRequestMethod("POST");
        homeConn.setRequestProperty("Content-Type", "application/json");
        homeConn.setDoOutput(true);

        try (OutputStream os = homeConn.getOutputStream()) {
            os.write(homeJson.getBytes("utf-8"));
        }

        JsonNode homeResponse = mapper.readTree(homeConn.getInputStream());
        int homeId = homeResponse.get("id").asInt();
        homeConn.disconnect();

        // Build JSON object x2
        String quoteJson = mapper.createObjectNode()
                .put("homeId", homeId)
                .put("customerId", customerId)
                .put("baseRate", 900.0)
                .toString();

        // Send API request to /v1/home_quote (This creates the home quote)
        URL quoteUrl = new URL("http://localhost:8080/v1/home_quote");
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

        HttpSession session = request.getSession();
        session.setAttribute("quoteType", "home");

        request.setAttribute("quote", result.toString());
        request.getRequestDispatcher("quoteSummary.jsp").forward(request, response);
    }
}
