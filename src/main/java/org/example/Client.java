package org.example;

import java.util.Scanner;

public class Client {

    public void start(){
        ServerHandler serverHandler = new ServerHandler("localhost",8090);

        Thread threadR = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    String read = serverHandler.read();
                    if(read.equals("")){
                        break;
                    }
                    System.out.println(serverHandler.read());
                }
            }
        });
        threadR.start();

        Thread threadW = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while(true){
                    String message = scanner.nextLine();
                    serverHandler.send(message);
                    if(message.equals("bye")){
                        break;
                    }
                }
            }
        });
    }

}
