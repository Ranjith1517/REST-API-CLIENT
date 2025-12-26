import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;



public class RestApiClient {

    public static void main(String[] args) {

        String apiUrl = "https://jsonplaceholder.typicode.com/users";

        try {

            URL url = new URL(apiUrl);

      
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            JSONArray usersArray = new JSONArray(response.toString());

   
            System.out.println("User Details:\n");

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);

                System.out.println("ID: " + user.getInt("id"));
                System.out.println("Name: " + user.getString("name"));
                System.out.println("Username: " + user.getString("username"));
                System.out.println("Email: " + user.getString("email"));

                JSONObject address = user.getJSONObject("address");
                System.out.println("City: " + address.getString("city"));

                System.out.println("-----------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Error occurred while consuming REST API.");
            e.printStackTrace();
        }
    }
}
