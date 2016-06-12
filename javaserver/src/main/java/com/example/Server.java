package com.example;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Gary on 16/5/28.
 */

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

/**
 * Created by Gary on 16/5/28.
 */
public class Server implements Runnable{
    private Thread thread;
    private ServerSocket servSock;
    private JTextArea textArea;

    public Server(JTextArea textArea){
        this.textArea = textArea;

        try {
            // Detect server ip
            InetAddress IP = InetAddress.getLocalHost();

            System.out.println("IP of my system is := "+IP.getHostAddress());
            System.out.println("Waitting to connect......");

            // Create server socket
            servSock = new ServerSocket(2000);

            // Create socket thread
            thread = new Thread(this);
            thread.start();
        } catch (java.io.IOException e) {
            System.out.println("Socket have some problems !");
            System.out.println("IOException :" + e.toString());
        } finally{

        }
    }

    @Override
    public void run(){
        // Running for waitting multiple client
        while(true){
            try{
                // After client connected, create client socket connect with client
                Socket clntSock = servSock.accept();
                InputStream in = clntSock.getInputStream();

                System.out.println("Connected!!");

                // Transfer data
                byte[] b = new byte[1024];
                int length;

                length = in.read(b);
                String s = new String(b);
                this.textArea.setText(s);
                System.out.println("[Server Said]" + s);

            }
            catch(Exception e){
                //System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
