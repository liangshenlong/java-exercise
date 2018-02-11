package com.liangsl.test.socket;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerApp extends Applet {
    ServerSocket serverSocket;
    TextField t;
    String msg = "";
    private static int count = 0;
    public void init(){
        setSize(300,300);
        Label label2 = new Label("listen port");
        add(label2);
        t = new TextField(20);
        add(t);
        Button button = new Button("begin");
        MyMouseListener myMouseListener = new MyMouseListener();
        button.addMouseListener(myMouseListener);
        add(button);

    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg,20,180);
    }

    class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                while (true){
                    Socket socket = serverSocket.accept();
                    msg = "connection success"+ count++;
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF(msg);
                    dataOutputStream.close();
                    socket.close();
                    System.out.println(msg);
                    repaint();
                }
            }catch (Exception ex){
                msg = ex.getMessage();
            }finally {
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            try{
                serverSocket= new ServerSocket(Integer.parseInt(t.getText()));
                msg = "port:"+t.getText()+" \n\r wait connection";
            }catch (Exception ex){

            }finally {
                repaint();
            }
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
