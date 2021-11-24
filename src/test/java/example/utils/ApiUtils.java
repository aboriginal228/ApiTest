package example.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiUtils {

    private static int responseCode;
    private static String responseMessage;
    public static boolean isEmpty = false;

    public static void getFromUrl(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            responseCode = con.getResponseCode();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                responseMessage = content.toString();
            }
            con.disconnect();

        } catch (FileNotFoundException e) {
            isEmpty = true;
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public static void postToUrl(String link, String body, String title, String id) {
        try {

            String formatString = "body=%s&title=%s&userId=%s";
            String urlParameters = String.format(formatString, body, title, id);
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            URL url = new URL(link);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);

            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                responseCode = conn.getResponseCode();
                responseMessage = content.toString();
            }
            conn.disconnect();

        } catch (FileNotFoundException e) {
            isEmpty = true;
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static int getResponseCode() {
        return responseCode;
    }

    public static String getResponseMessage() {
        return responseMessage;
    }
}
