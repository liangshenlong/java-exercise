package com.liangsl.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOSocketServer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);
    public static void main(String[] args) {
        ServerSocket echoServer = null;
        Socket clientSocket = null;
        try {
            echoServer = new ServerSocket(9000);
        } catch (IOException e) {
            System.out.println(e);
        }
        while (true) {
            try {
                clientSocket = echoServer.accept();
                executorService.execute(new SocketHandle(clientSocket));
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


    static class SocketHandle implements Runnable{
        Socket clientSocket;
        public SocketHandle(Socket clientSocket){
            this.clientSocket = clientSocket;
        }
        public void run(){
            System.out.println(clientSocket.getRemoteSocketAddress()
                    + " connect!");
            BufferedReader is = null;
            PrintWriter os = null;
            try {
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream(), true);
                // 从InputStream当中读取客户端所发送的数据
                String inputLine = null;
                long b=System.currentTimeMillis ();
                while ((inputLine = is.readLine()) != null)
                {
                    inputLine += b;
                    os.println(inputLine);
                }
                long e=System.currentTimeMillis ();
                System. out.println ("spend:"+(e - b)+" ms ");
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    is.close();
                    os.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
