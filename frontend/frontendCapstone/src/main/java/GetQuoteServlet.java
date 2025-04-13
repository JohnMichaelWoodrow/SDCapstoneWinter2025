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
import java.util.Objects;


@WebServlet("/getQuote")
public class GetQuoteServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quoteId = request.getParameter("quoteId");
        String quoteType = request.getParameter("quoteType");

        URL quoteUrl = new URL("http://localhost:8080/v1/quote/" + quoteId);
        HttpURLConnection quoteConn = (HttpURLConnection) quoteUrl.openConnection();
        quoteConn.setRequestMethod("GET");
        quoteConn.setRequestProperty("Content-Type", "application/json");

        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(quoteConn.getInputStream(), "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line.trim());
            }
        }

        quoteConn.disconnect();

        if (Objects.equals(quoteType, "Home")) {
            HttpSession session = request.getSession();
            session.setAttribute("quoteType", "home");
        } else if (Objects.equals(quoteType, "Auto")) {
            HttpSession session = request.getSession();
            session.setAttribute("quoteType", "vehicle");
        }

        // Forward the customer JSON string to the JSP page
        request.setAttribute("quote", result.toString());
        request.getRequestDispatcher("quoteSummary.jsp").forward(request, response);
    }
}
