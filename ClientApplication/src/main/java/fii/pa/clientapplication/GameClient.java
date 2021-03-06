/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fii.pa.clientapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Mally
 */
public class GameClient {

    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        try (
                 Socket socket = new Socket(serverAddress, PORT);  PrintWriter out = new PrintWriter(socket.getOutputStream(), true);  BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Type your request:");
                String request = scanner.nextLine();
                if (request.equals("exit")) {
                    break;
                } else {
                    out.println(request);
                    String response = in.readLine();
                    if (response == null) {
                        System.out.println("Server stopped");
                        break;
                    }
                    System.out.println(response);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
