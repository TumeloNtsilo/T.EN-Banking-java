package za.co.tumelo.client;

import za.co.tumelo.CreateAccount;

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
        welcome();



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

    public static void welcome(){
        System.out.println("Welcome to T.EN Bank");
        System.out.println("Where trust and the elegant of banking meet");
        System.out.println();
        System.out.println("Do you have an account? (y/n)");

        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
            createNewAccount(input);
            System.out.println("Now go on and login: ");
            loginUser();

        } else if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){
            loginUser();
        }
    }

    public static void createNewAccount(){
        System.out.println("Now let us open one for you.");
        CreateAccount createAccount = new CreateAccount();
        createAccount.enterDetails();
        createAccount.printPersonalDetails();
        System.out.println("Here is your pin number, use it login");
        System.out.println("Pin: " + createAccount.createPin());
        System.out.println("Please keep it safe, and do not forget it.");
    }

    public static void loginUser(){
        System.out.print("Please enter your pin to login: ");
        int pin = sc.nextInt();
    }


}
