/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpclient;

/**
 *
 * @author domenico
 */

import java.io.*;
import java.net.*;
import java.util.List;
import com.google.gson.Gson;

public class HttpClient {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */

    
    private static HttpURLConnection con;

    public static void main(String[] args) throws MalformedURLException,
            ProtocolException, IOException {

        String url = "http://localhost:8080/accelerometer";
        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());
            System.out.println("Creating the object position\n");
            // Now do the magic.
            LevelPosition data = new Gson().fromJson(content, LevelPosition.class);

            // Show it.
            System.out.println(data);
            
            

        } finally {
            
            con.disconnect();
        }
    }
}