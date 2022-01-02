package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class APIRequester {
    private PropertiesLoader prop = new PropertiesLoader();
    private String protocol;
    private String endpoint;
    private String authToken;


    public APIRequester() {
        this.authToken = this.prop.getProps().getProperty("authToken");
    }

    public Response getRequest(String url) {
        Response response = null;
        StringBuilder result = new StringBuilder();

        try {
            URL requestUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + this.authToken);
            //conn.setRequestProperty("Content-Type", "application/json");
            if(conn.getResponseCode() == 200) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        result.append(line);
                    }
                }
                response = new Response(conn.getResponseCode(), result.toString(), conn.getURL().toString());
            }else{
                response = new Response(conn.getResponseCode(), result.toString(), conn.getURL().toString());

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
