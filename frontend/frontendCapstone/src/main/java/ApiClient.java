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

//    public String loginUser(String email) throws IOException, JSONException {
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        JSONObject json = new JSONObject();
//        json.put("email", email);
//
//        RequestBody body = RequestBody.create(JSON, json.toString());
//
//        Request request = new Request.Builder()
//                .url("http://localhost:8080/v1" + "/user") // use your actual endpoint
//                .post(body)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Login failed: " + response);
//            return response.body().string();
//        }
//    }
}
