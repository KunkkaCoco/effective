package com.effective.exec;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenweichao on 15-5-5.
 */
public class VirusScanner {

    private static JFrame appFrame;
    private static JLabel statusString;
    private int scanNumber = 0;

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    private GregorianCalendar calendar = new GregorianCalendar();
    private static VirusScanner app = new VirusScanner();

    public void scanDisk() {
        final  Runnable scanner = new Runnable() {
            public void run() {
                try {
                    appFrame.setVisible(true);
                    scanNumber++;
                    Calendar cal = Calendar.getInstance();
                    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
                    String s = " Scan "+ scanNumber + " started an "+ df.format(cal.getTime());
                    System.out.println(s);
                    statusString.setText(s);
                    Thread.sleep(1000+new Random().nextInt(10000));
                    appFrame.setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        final ScheduledFuture<?> scanManager = scheduler.scheduleAtFixedRate(scanner,1,15, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                scanManager.cancel(true);
                scheduler.shutdown();
                appFrame.dispose();
                                    }
        },120,TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        appFrame = new JFrame();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        appFrame.setSize(400, 70);
        appFrame.setLocation(dimension.width / 2 - appFrame.getWidth() / 2, dimension.height / 2 - appFrame.getWidth() / 2);
        statusString = new JLabel();
        appFrame.add(statusString);
        appFrame.setVisible(false);
        app.scanDisk();
    }
}
