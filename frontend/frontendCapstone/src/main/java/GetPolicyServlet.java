import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/getPolicy")
public class GetPolicyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String policyId = request.getParameter("policyId");

        ObjectMapper mapper = new ObjectMapper();

        // Get Policy from policy table
        URL policyUrl = new URL("http://localhost:8080/v1/policy/" + policyId);
        HttpURLConnection policyConn = (HttpURLConnection) policyUrl.openConnection();
        policyConn.setRequestMethod("GET");

        StringBuilder policyJson = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(policyConn.getInputStream(), "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                policyJson.append(line);
            }
        }

        policyConn.disconnect();

        JsonNode policyNode = mapper.readTree(policyJson.toString());
        int quoteId = policyNode.get("quoteId").asInt();
        String policyType = policyNode.get("policyType").asText();

        // Home or Auto
        String quoteEndpoint;
        if (policyType.equalsIgnoreCase("Home")) {
            quoteEndpoint = "http://localhost:8080/v1/home_quote/" + quoteId;
        } else {
            quoteEndpoint = "http://localhost:8080/v1/auto_quote/" + quoteId;
        }

        // Get Policy from auto/home policy table
        URL quoteUrl = new URL(quoteEndpoint);
        HttpURLConnection quoteConn = (HttpURLConnection) quoteUrl.openConnection();
        quoteConn.setRequestMethod("GET");

        StringBuilder quoteJson = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(quoteConn.getInputStream(), "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                quoteJson.append(line);
            }
        }

        quoteConn.disconnect();

        request.setAttribute("policy", quoteJson.toString());
        request.getRequestDispatcher("policyDetails.jsp").forward(request, response);
    }
}
