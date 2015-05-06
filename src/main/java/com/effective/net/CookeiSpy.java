package com.effective.net;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by chenweichao on 15-5-6.
 */
public class CookeiSpy {
    private static final String TIME_FORMAT_NOW = "HH:mm:ss";
    public static final SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_NOW);

    public static void main(String[] args) {
        String urlstr = "http://www.oschina.com";
        CookieManager manager = new CookieManager();
        manager.setCookiePolicy(new CustomCookiePolicy());
        try {
            URL url = new URL(urlstr);
            URLConnection connection = url.openConnection();
            List<HttpCookie> cookies = manager.getCookieStore().getCookies();
            for (HttpCookie cookie : cookies) {
                System.out.println("Name: " + cookie.getName());
                System.out.println("Domain: " + cookie.getDomain());
                long age = cookie.getMaxAge();
                if (age == -1) {
                    System.out.println("This cookie will expire when browser closes");
                } else {
                    System.out.printf("This cookie will expire in %s seconds%n", sdf.format(age));
                }
                System.out.println("Secured: " + ((Boolean)cookie.getSecure()));
                System.out.println("Value: " + cookie.getValue());
                System.out.println();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CustomCookiePolicy implements CookiePolicy {
    public boolean shouldAccept(URI uri, HttpCookie cookie) {
        return true;
    }

}