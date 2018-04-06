package com.liangsl.java;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

public class NIOSocketClient {
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;
    private String host;
    private int port;
    private static final int sleep_time=1000;//*1000*1000

    public NIOSocketClient(String host,int port){
        this.host = host;
        this.port = port;
    }


    public void send() throws IOException {
        init();
        doSend();
    }

    private void doSend() throws IOException {
        while (!stop){
            selector.select();
            Set selectionKeys= selector.selectedKeys();
            Iterator<SelectionKey> i = selectionKeys.iterator();
            while (i.hasNext()){
                SelectionKey key = i.next();
                i.remove();
                try {
                    handleSend(key);
                } catch (Exception e) {
                    if(key != null) {
                        key.cancel();
                        if(key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }

            }
        }
    }

    private void handleSend(SelectionKey key) throws IOException {
        if(key.isValid()){
            SocketChannel sc = (SocketChannel) key.channel();
            if(key.isConnectable()) {
                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                }
            }

            if(key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes>0){
                    System.out.println("from server:");
                    readBuffer.flip();
                    App.byteBufferToString(readBuffer);
                }
                this.stop = true;
            }
        }
    }

    private void doWrite(SocketChannel sc) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("Hello!".getBytes());
        LockSupport.parkNanos(sleep_time);//增加socket通信时间
        byteBuffer.put("this\t".getBytes());
        LockSupport.parkNanos(sleep_time);
        byteBuffer.put("is\t".getBytes());
        LockSupport.parkNanos(sleep_time);
        byteBuffer.put("IO\t".getBytes());
        LockSupport.parkNanos(sleep_time);
        byteBuffer.put("java!".getBytes());
        byteBuffer.flip();
        sc.write(byteBuffer);
        if(!byteBuffer.hasRemaining()) {
            System.out.println("send request 2 server success.");
        }
    }

    private void init() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        socketChannel.connect(new InetSocketAddress(host,port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }


    public static void main(String[] args) throws IOException {
        NIOSocketClient nioSocketClient = new NIOSocketClient("localhost",9000);
        nioSocketClient.send();
    }
}
