package com.effective.io;
import java.io.*;

/**
 * Created by chenweichao on 15-3-31.
 */
public class FIleLength {
    public static void main(String[] args) {
        int count = 0;
        InputStream is = null;
        if (args.length < 1){
            System.out.println("Usage : java FileLength <filename>");
            System.exit(0);
        }
        try {
            is = new FileInputStream(args[0]);
            while (is.read() != -1){
                count++;
            }

            System.out.println(args[0] + " length = "+count);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
