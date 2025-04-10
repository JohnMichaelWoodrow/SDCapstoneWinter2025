import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ApiClient {
    private final OkHttpClient client = new OkHttpClient();

    public String getAllUsers() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/v1" + "/user")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string(); // This is the raw JSON string
        }
    }

    public String getAllCustomers() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/v1" + "/customer")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string(); // This is the raw JSON string
        }
    }
}
