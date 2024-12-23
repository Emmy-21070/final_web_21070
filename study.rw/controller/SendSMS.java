package controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SendSMS {
    public static void main(String[] args) {
        // TextMagic API credentials
        String apiKey = "your_api_key";  // Replace with your API key
        String username = "your_username";  // Replace with your TextMagic username

        // TextMagic API endpoint
        String apiUrl = "https://rest.textmagic.com/api/v2/messages";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Create HTTP POST request
            HttpPost post = new HttpPost(apiUrl);

            // Add headers
            post.setHeader("X-TM-Username", username);
            post.setHeader("X-TM-Key", apiKey);
            post.setHeader("Content-Type", "application/json");

            // Prepare JSON body with SMS details
            JsonObject json = new JsonObject();
            json.addProperty("text", "Hello! This is a message sent via TextMagic API.");
            json.addProperty("phones", "1234567890"); // Replace with recipient's phone number

            // Set JSON body in the POST request
            StringEntity entity = new StringEntity(json.toString());
            post.setEntity(entity);

            // Execute the POST request
            CloseableHttpResponse response = client.execute(post);

            // Read the response
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Close the response
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
