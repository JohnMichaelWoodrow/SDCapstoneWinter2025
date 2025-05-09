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

@WebServlet("/purchase")
public class purchaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int quoteStatus = 0;

        HttpSession session = request.getSession();
        String quoteType = (String) session.getAttribute("quoteType");

        String quoteId = request.getParameter("id");
        String userId = request.getParameter("userId");

        if ("Auto".equalsIgnoreCase(quoteType)) {
            // Mark the quote as paid
            URL quoteUrl = new URL("http://localhost:8080/v1/auto_quote/" + quoteId + "/paid?paid=true");
            HttpURLConnection quoteConn = (HttpURLConnection) quoteUrl.openConnection();
            quoteConn.setRequestMethod("PUT");
            quoteConn.setRequestProperty("Content-Type", "application/json");
            quoteConn.setDoOutput(true);

            quoteStatus = quoteConn.getResponseCode();
            quoteConn.disconnect();
        } else if ("Home".equalsIgnoreCase(quoteType)) {
            // Mark the quote as paid
            URL quoteUrl = new URL("http://localhost:8080/v1/home_quote/" + quoteId + "/paid?paid=true");
            HttpURLConnection quoteConn = (HttpURLConnection) quoteUrl.openConnection();
            quoteConn.setRequestMethod("PUT");
            quoteConn.setRequestProperty("Content-Type", "application/json");
            quoteConn.setDoOutput(true);

            quoteStatus = quoteConn.getResponseCode();
            quoteConn.disconnect();
        }

        // Create the policy if quote was marked as paid
        if (quoteStatus == 200 || quoteStatus == 204) {

            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.createObjectNode()
                    .put("customerId", Integer.parseInt(userId))
                    .put("quoteId", Integer.parseInt(quoteId))
                    .toString();

            URL policyUrl = new URL("http://localhost:8080/v1/policy");
            HttpURLConnection policyConn = (HttpURLConnection) policyUrl.openConnection();
            policyConn.setRequestMethod("POST");
            policyConn.setRequestProperty("Content-Type", "application/json");
            policyConn.setDoOutput(true);

            try (OutputStream os = policyConn.getOutputStream()) {
                os.write(jsonBody.getBytes("utf-8"));
            }

            int policyStatus = policyConn.getResponseCode();
            policyConn.disconnect();

            if (policyStatus == 201 || policyStatus == 200) {
                request.setAttribute("purchaseSuccess", "Policy created successfully.");
            } else {
                request.setAttribute("purchaseError", "Failed to create policy.");
            }
        } else {
            request.setAttribute("purchaseError", "Failed to mark quote as paid.");
        }

        session.removeAttribute("quoteType");

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
    }
}
