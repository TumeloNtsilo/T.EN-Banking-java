package za.co.tumelo.server;

import java.io.IOException;
import java.util.Scanner;

public class ServerCommands implements Runnable{

    @Override
    public void run() {
        try(Scanner sc = new Scanner(System.in)){
            while (true){
                String command = sc.nextLine();
                switch (command.trim().toLowerCase()) {
                    case "save" -> saveClientDetails();
                    case "quit" -> {
                        quitCommand();
                        return;
                    }
                    default -> System.out.println("Invalid command");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveClientDetails(){

    }

    public void quitCommand() throws IOException {
        for(ClientHandler client: Server.getClients()){
            client.sendMessage("quit");
        }
        Server.closeQuietly();
        System.exit(0);

    }

}
