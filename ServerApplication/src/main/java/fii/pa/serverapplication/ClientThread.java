/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fii.pa.serverapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Mally
 */
class ClientThread extends Thread {

    private Socket socket = null;
    private GameServer server = null;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String request = in.readLine();
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (request != null && server.getRunning()) {
                String response = execute(request);
                out.println(response);
                out.flush();
                request = in.readLine();
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private String execute(String request) throws IOException {
        if (request.equals("stop")) {
            server.setRunning(false);
            return "Server stopped";
        } else {
            return "Server received the request " + request;
        }
    }
}
