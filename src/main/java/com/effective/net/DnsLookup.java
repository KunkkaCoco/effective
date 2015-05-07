package com.effective.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by chenweichao on 15-5-7.
 */
public class DnsLookup {
    public static void main(String[] args) {
        InetAddress[] inetHost = null;

        System.out.println("List of Google servers");
        try {
            inetHost = InetAddress.getAllByName("www.baidu.com");
            for (InetAddress address : inetHost) {
                System.out.println(address);
            }
            System.out.println("\n List of CNN servers ");
            inetHost = InetAddress.getAllByName("cnn.com");
            for (InetAddress address : inetHost) {
                System.out.println(address);
            }
            System.out.println("\nLocal machine ");
            System.out.println(InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
