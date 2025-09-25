package za.co.tumelo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    private static final int Port = 6000;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ServerSocket serverSocket;


    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(Port);
        System.out.println("Server started, waiting for client to connect.");

        ServerCommands serverCommands = new ServerCommands();
        Thread adminCommands = new Thread(serverCommands);
        adminCommands.setDaemon(true);
        adminCommands.start();


        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("New client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);
                addClients(clientHandler);
                new Thread(clientHandler).start();

            } catch (IOException e) {
                if (serverSocket.isClosed()) {
                    System.out.println("--------SERVER IS CLOSED--------");
                    break;
                }
                System.err.println("Error connecting client " + e.getMessage());
            }
        }
    }

    public static void addClients(ClientHandler client){
        clients.add(client);
    }

    public static ArrayList<ClientHandler> getClients() {
        return clients;
    }

    public static void closeQuietly() throws IOException {
       if(!serverSocket.isClosed()){
           System.out.println("Closing server....");
           serverSocket.close();
       }
    }

}
