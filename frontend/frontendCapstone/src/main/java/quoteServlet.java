import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;

@WebServlet("/get-quote")
public class quoteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Collect form data
            String dobString = request.getParameter("driverAge");
            String accidentCountStr = request.getParameter("accidentCount");
            String vehicleYearStr = request.getParameter("vehicleYear");
            String vehicleValueStr = request.getParameter("vehicleValue");

            // Convert values
            LocalDate dob = LocalDate.parse(dobString);
            int driverAge = Period.between(dob, LocalDate.now()).getYears();
            int accidentCount = Integer.parseInt(accidentCountStr);
            int vehicleYear = Integer.parseInt(vehicleYearStr);
            double vehicleValue = Double.parseDouble(vehicleValueStr);

            // Build JSON
            JSONObject json = new JSONObject();
            json.put("driverAge", driverAge);
            json.put("accidentCount", accidentCount);
            json.put("vehicleYear", vehicleYear);
            json.put("vehicleValue", vehicleValue);

            // Send API request
            URL url = new URL("http://localhost:8080/v1/quote/auto");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            StringBuilder result = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line.trim());
                }
            }

            conn.disconnect();
            request.setAttribute("quote", result.toString());
            request.getRequestDispatcher("quoteSummary.jsp").forward(request, response);

        } catch (JSONException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON.");
        } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric or date input.");
        }
    }
}
