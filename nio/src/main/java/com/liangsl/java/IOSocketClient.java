package com.liangsl.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.locks.LockSupport;

public class IOSocketClient {
    private static final int sleep_time=1000*1000*1000;
    public static void main(String[] args) throws IOException {
        Socket client = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        try {
            long b=System.currentTimeMillis ();
            client = new Socket();
            client.connect(new InetSocketAddress("localhost", 9000));
            writer = new PrintWriter(client.getOutputStream(), true);
            writer.print("Hello!");
            LockSupport.parkNanos(sleep_time);//增加socket通信时间
            writer.print("this\t");
            LockSupport.parkNanos(sleep_time);
            writer.print("is\t");
            LockSupport.parkNanos(sleep_time);
            writer.print("IO\t");
            LockSupport.parkNanos(sleep_time);
            writer.print("java!");
            writer.println();
            writer.flush();
            reader = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            System.out.println("from server: " + reader.readLine());
            long e=System.currentTimeMillis ();
            System. out.println ("spend:"+(e - b)+" ms ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
            writer.close();
            reader.close();
        }
    }
}
