package za.co.tumelo.client;

import org.json.JSONObject;
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
                    if(response.contains("quit")){
                        System.out.println("Closing Server, Goodbye!\n------------Server is closed-----------");
                        System.exit(0);
                    }
                    System.out.println(response);
                }
            } catch (IOException e) {
                System.out.println("Disconnected, please login again.");;
            }

        }).start();
        welcome(out);
        options();

        String command;
        CommandHandler handler = new CommandHandler();
        while ((command = getUserInput("\nType here: "))!= null) {
            if (!checkConnection(socket)){
                System.out.println("--------SERVER IS CLOSED--------");
                break;
            }

            handler.handle(command, out, sc);
        }
        socket.close();
        System.exit(0);
    }

    private static String getUserInput(String input) {
        String userInput = "";
        while (userInput.isBlank()) {
            System.out.println(input);
            userInput = sc.nextLine().trim();
        }
        return userInput;
    }


    public static boolean checkConnection(Socket socket) {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }

    public static void welcome(PrintWriter out){
        System.out.println("Welcome to T.EN Bank");
        System.out.println("Where trust and the elegant of banking meet");
        System.out.println();
        System.out.println("Do you have an account? (y/n)");

        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
            createNewAccount(out);
            System.out.println("Now go on and login: ");
            loginUser(out);

        } else if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){
            loginUser(out);
        }
    }

    public static void createNewAccount(PrintWriter out){
        System.out.println("\nNow let us open one for you.");
        CreateAccount createAccount = new CreateAccount();
        createAccount.enterDetails();
        createAccount.printPersonalDetails();

        int pin = createAccount.createPin();
        System.out.println("Here is your pin number, use it login");
        System.out.println("Pin: " + pin);
        System.out.println("Please keep it safe, and do not forget it.");

        JSONObject details = createAccount.getPersonalDetails();
        details.put("action", "register");
        details.put("pin", pin);

        out.println(details.toString());

    }

    public static void loginUser(PrintWriter out){
        System.out.println("\nPlease enter your pin to login: ");
        String pin = sc.nextLine();
        JSONObject login = new JSONObject();

        login.put("action", "login");
        login.put("pin", pin);
        out.println(login.toString());

    }

    public static void options(){
        System.out.println("How can we help you today?");
        System.out.println("1. withdraw\n2. deposit\n3. balance\n4. statement\n5. exit");
    }


}