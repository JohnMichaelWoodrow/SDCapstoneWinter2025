import java.io.IOException;

public class TestApiClient {
    public static void main(String[] args) throws IOException, IOException {
        ApiClient client = new ApiClient();
        String json = client.getAllUsers();
        System.out.println(json);
    }
}
