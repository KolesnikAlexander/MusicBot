package com.gmail.alex60070;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.gmail.alex60070.Settings.LISTING_SERVER_URL;

/**
 * Created by alex60070 on 29.07.17.
 */
public class ListingDownloader {

    private StringBuffer response;

    public InputStream listing(byte[] asmFile) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(LISTING_SERVER_URL).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length",String.valueOf(asmFile.length));

            // For POST only - START
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(asmFile);
            os.flush();
            os.close();
            // For POST only - END

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //RESPONSE
        int responseCode;
        try {
            responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\r\n");
            }
            in.close();
        } else {
            System.out.println("POST request did not worked");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(response.toString().getBytes());
    }
}
