import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final String API_KEY = "hbwiuefIjbJD";
    private final String SEARCH_ENGINE_ID = "SAMPLE SEARCH ENGINE";

    public List<String> search(String query) {
        List<String> results = new ArrayList<>();
        // Implement local search algorithm here
        // Add search results to the 'results' list
        return results.subList(0, Math.min(results.size(), 20)); // Limit results to first 20
    }

    public List<String> searchWeb(String query) {
        List<String> results = new ArrayList<>();
        try {
            URL url = new URL("https://www.googleapis.com/customsearch/v1?key=" + API_KEY + "&cx=" + SEARCH_ENGINE_ID + "&q=" + URLEncoder.encode(query, "UTF-8") + "&num=20&fields=items(title,link)");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String json = response.toString();
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonArray items = jsonObject.getAsJsonArray("items");

            for (JsonElement item : items) {
                String title = item.getAsJsonObject().get("title").getAsString();
                String link = item.getAsJsonObject().get("link").getAsString();
                results.add(title + " (" + link + ")");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
