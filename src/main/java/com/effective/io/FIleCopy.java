package com.effective.io;
import java.io.*;

/**
 * Created by chenweichao on 15-3-31.
 */
public class FIleCopy {
    public static void main(String[] args) {
        int numberRead = 0;
        InputStream readerStream = null;
        OutputStream writerStream = null;
        byte buffer[] = new byte[512];
        if (args.length < 1) {
            System.out.println("Usage : java FileCopy file1 file2");
            System.exit(0);
        }
        try {
            readerStream = new FileInputStream(args[0]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            writerStream = new FileOutputStream(args[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            while ((numberRead = readerStream.read(buffer)) != -1) {
                writerStream.write(buffer, 0, numberRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                readerStream.close();
                writerStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("1 file copied!");

    }
}
