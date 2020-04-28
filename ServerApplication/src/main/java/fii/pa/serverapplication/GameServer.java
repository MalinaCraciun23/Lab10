/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fii.pa.serverapplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 *
 * @author Mally
 */
public class GameServer {

    public static final int PORT = 8100;
    private Boolean running = false;
    ServerSocket serverSocket = null;

    public GameServer() throws IOException {
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(3000);
            running = true;
            while (running) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("A client has connected");
                    new ClientThread(socket, this).start();
                } catch (SocketTimeoutException e) {
                    //ignore timeout
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            serverSocket.close();
            System.out.println("Server stopped");
        }
    }

    /**
     * @return the running
     */
    public Boolean getRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(Boolean running) {
        this.running = running;
    }

    public static void main(String[] args) throws IOException {
        new GameServer();
    }

}
