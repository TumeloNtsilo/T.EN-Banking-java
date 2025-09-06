package za.co.tumelo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int port = 6000;
    private static final String host = "localhost";
    private static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread (() -> {
            try{
                String response;
                while((response = in.readLine()) != null){
                    System.out.println(response);
                }
            } catch (IOException e) {
                System.out.println("Disconnected, please login again.");;
            }

        }).start();

        String input;
        while ((input = getUserInput("Type here: "))!= null) {
            if (!checkConnection(socket)){
                System.out.println("--------SERVER IS CLOSED--------");
                break;
            }

            boolean isValid = CommandHandler(input);
            if (isValid){
                System.out.println(input);
            }else if (!isValid || input == null){
                System.out.println("input not valid.");
                continue;
            }
        }
    }

    private static String getUserInput(String input) {
        System.out.println(input);
        String userInput = sc.nextLine();
        while (userInput.isBlank()) {
            System.out.println(input);
            userInput = sc.nextLine();
        }
        return userInput;
    }


    public static boolean checkConnection(Socket socket) {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }

    private static boolean CommandHandler(String input) {
        // accept anything that’s not blank
        return input != null && !input.isBlank();
    }


}
