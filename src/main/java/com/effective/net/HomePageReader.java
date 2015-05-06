package com.effective.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by chenweichao on 15-5-6.
 */
public class HomePageReader {

    public static void main(String[] args) {
        Socket socketObject = null;
        try {
            socketObject = new Socket("localhost", 10000);

            try {
                OutputStream outStream = socketObject.getOutputStream();
                String str = "GET / HTTP/1.0\n\n";
                outStream.write(str.getBytes());
                InputStream inputStream = socketObject.getInputStream();
                Scanner reader = new Scanner(inputStream);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                socketObject.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
