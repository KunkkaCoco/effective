package com.effective.net;


import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by chenweichao on 15-5-6.
 */
public class WebPageReader {
    public static void main(String[] args) {

        String strURL = "http://www.oracle.com/us/technologies/java/index.html";
        try {
            URL url = new URL(strURL);
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            java.util.Scanner reader = new Scanner(in);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
