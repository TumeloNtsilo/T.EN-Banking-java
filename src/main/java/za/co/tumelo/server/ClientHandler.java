package za.co.tumelo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
    }


    public boolean validateCommand(String command){


        return false;
    }





    @Override
    public void run() {
        try{
            while(in.readLine() != null){
                String command = in.readLine();
                if(validateCommand(command)){
                    System.out.println();
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
