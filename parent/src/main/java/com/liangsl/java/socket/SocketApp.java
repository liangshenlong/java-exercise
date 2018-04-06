package com.liangsl.java.socket;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketApp extends Applet{
    TextField t1,t2;
    String serverInfo = "";
    public void init(){
        Label label1 = new Label("address");
        add(label1);
        t1 = new TextField(20);
        add(t1);
        Label label2 = new Label("port");
        add(label2);
        t2 = new TextField(20);
        add(t2);
        Button button = new Button("send");
        MyMouseListener myMouseListener = new MyMouseListener();
        button.addMouseListener(myMouseListener);
        add(button);

    }

    @Override
    public void paint(Graphics g) {
        g.drawString(serverInfo,20,180);
    }

    class MyMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            Socket socket = null;
            try {
                String address = t1.getText();
                String port = t2.getText();
                int portInt = Integer.parseInt(port);
                if("localhost".equals(address)){
                    socket = new Socket(InetAddress.getLocalHost(),portInt);
                }else{
                    socket = new Socket(InetAddress.getByName(address),portInt);
                }

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                serverInfo = dataInputStream.readUTF();
                socket.close();
            }catch (Exception ex){
                serverInfo = ex.getMessage();
            }finally {
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}

