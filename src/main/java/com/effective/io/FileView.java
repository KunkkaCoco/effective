package com.effective.io;
import java.io.*;

/**
 * Created by chenweichao on 15-3-31.
 */
public class FileView{
    public static void main(String[] args) {
        {
            int numberRead = 0;
            FileReader reader = null;
            PrintWriter writer = null;
            char buffer[] = new char[512];
            if (args.length < 1) {
                System.out.println("Usage : java FileCopy file1 file2");
                System.exit(0);
            }
            try {
                reader = new FileReader(args[0]);
                writer = new PrintWriter(System.out);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }

            try {
                while ((numberRead = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, numberRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("1 file copied!");

        }
    }
}
