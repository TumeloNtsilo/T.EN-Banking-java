package za.co.tumelo.client;

import org.json.JSONObject;
import za.co.tumelo.CreateAccount;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 6000;
    private static final String HOST = "localhost";
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            new Thread(() -> listenForServerMessages(in, out)).start();

            displayWelcomeMessage();
            handleAccountChoice(out);

            CommandHandler handler = new CommandHandler();
            while (checkConnection(socket)) {
                String command = sc.nextLine().trim();
                if (!command.isBlank()) {
                    handler.handle(command, out, sc);
                }
            }

            System.out.println("Connection closed. Goodbye!");
        } catch (IOException e) {
            System.out.println("Error: Unable to connect to server. Please try again later.");
        }
    }

    private static void listenForServerMessages(BufferedReader in, PrintWriter out) {
        try {
            String response;
            while ((response = in.readLine()) != null) {
                if (response.startsWith("Hello")) {
                    System.out.println("\n" + response);
                }
                else if (response.contains("quit")) {
                    System.out.println("\nServer has been closed. Goodbye!");
                    System.exit(0);
                }
                else if (response.contains("Pin is correct")) {
                    displayOptions();
                    System.out.print("\nEnter a command (type 'help' to see options): ");
                }
                else if (response.contains("Incorrect pin")) {
                    System.out.println("\nIncorrect PIN. Please try again.");
                    loginUser(out);
                }
                else {
                    System.out.println(response);
                }
            }
        } catch (IOException e) {
            System.out.println("Lost connection to the server. Please restart and login again.");
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println("=====================================");
        System.out.println("     Welcome to T.EN Bank   ");
        System.out.println(" Where Trust Meets the Art of Banking ");
        System.out.println("=====================================\n");
    }

    private static void handleAccountChoice(PrintWriter out) {
        String input;
        do {
            System.out.print("Do you have an account? (y/n): ");
            input = sc.nextLine().trim().toLowerCase();
        } while (!input.equals("y") && !input.equals("n"));

        if (input.equals("n")) {
            createNewAccount(out);
            System.out.println("\nAccount created successfully!");
            System.out.println("Please log in to continue.\n");
            loginUser(out);
        } else {
            loginUser(out);
        }
    }

    private static void createNewAccount(PrintWriter out) {
        System.out.println("\nLet's create your new account.");
        CreateAccount createAccount = new CreateAccount();
        createAccount.enterDetails();
        createAccount.printPersonalDetails();

        int pin = createAccount.createPin();
        System.out.println("\nHere is your new PIN: " + pin);
        System.out.println("Please keep it safe and do not share it with anyone.");

        JSONObject details = createAccount.getPersonalDetails();
        details.put("action", "register");
        details.put("pin", pin);

        out.println(details.toString());
    }

    private static void loginUser(PrintWriter out) {
        System.out.print("\nPlease enter your PIN to log in: ");
        int pin = readIntInput();
        JSONObject login = new JSONObject();
        login.put("action", "login");
        login.put("pin", pin);

        out.println(login.toString());
    }

    private static void displayOptions() {
        System.out.println("\nAvailable Services:");
        System.out.println("- Withdraw");
        System.out.println("- Deposit");
        System.out.println("- Check Balance");
        System.out.println("- View Statement");
        System.out.println("️- Exit");
    }

    private static int readIntInput() {
        while (true) {
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter digits only: ");
            }
        }
    }

    private static boolean checkConnection(Socket socket) {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }
}
