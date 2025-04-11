import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/get-home-quote")
public class HomeQuoteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String homeValueStr = request.getParameter("homeValue");
            String location = request.getParameter("location");
            String yearBuiltStr = request.getParameter("yearBuilt");
            String heatingType = request.getParameter("heatingType");

            double homeValue = Double.parseDouble(homeValueStr);
            int yearBuilt = Integer.parseInt(yearBuiltStr);

            // Build JSON
            JSONObject json = new JSONObject();
            json.put("homeValue", homeValue);
            json.put("location", location);
            json.put("yearBuilt", yearBuilt);
            json.put("heatingType", heatingType);

            // Send to backend
            URL url = new URL("http://localhost:8080/v1/quote/home");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            InputStream inputStream;
            int status = conn.getResponseCode();

            if (status >= 200 && status < 300) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }

            StringBuilder result = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line.trim());
                }
            }

            conn.disconnect();
            System.out.println("Home quote API response: " + result);

            request.setAttribute("quote", result.toString());
            request.getRequestDispatcher("quoteSummary.jsp").forward(request, response);

        } catch (JSONException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid home quote data.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric input.");
        }
    }
}
