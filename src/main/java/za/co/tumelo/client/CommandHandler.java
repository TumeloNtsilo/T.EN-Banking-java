package za.co.tumelo.client;

import org.json.JSONObject;
import za.co.tumelo.Response;
import za.co.tumelo.command.*;

import java.io.PrintWriter;
import java.util.Scanner;

public class CommandHandler {
    private final Response response = new Response();


    public void printHelp(){
        System.out.println(
                """                    
                        
                        Please try any of the following prompts...
                        
                        "balance"
                        "deposit"
                        "withdraw"
                        "exit".
                        
                        Type any command e.g "withdraw"
                       
                        """
        );
    }

    public boolean isValid(String command){
        if(!commandLength(command)){
            return false;
        }
        return command.equalsIgnoreCase("deposit") ||
                command.equalsIgnoreCase("withdraw") ||
                command.equalsIgnoreCase("balance") ||
                command.equalsIgnoreCase("exit");
    }


    private boolean commandLength(String command){
        String[] c = command.split(" ");
        if(c.length == 1){
            return true;
        }
        return false;
    }


    public void handle(String command, PrintWriter out, Scanner sc){
        if(!isValid(command)){
            printHelp();
            return;
        }

        switch (command){
            case "balance" -> {
                JSONObject request = new JSONObject();
                request.put("action", "balance");

                out.println(request.toString());
            }

            case "withdraw" -> {
                JSONObject request = new JSONObject();

                System.out.println("Withdraw from which account, credit or savings? (type credit or savings)");
                String account = sc.nextLine();

                long money = moneyToWithdraw(sc);
                request.put("action", "withdraw");
                request.put("account", account);
                request.put("amount", money);
                out.println(request.toString());
            }

            case "deposit" -> {
                JSONObject request = new JSONObject();

                System.out.println("Enter the account you want to deposit to: (savings or credit)");
                String accountName = sc.nextLine();

                long money = moneyToDeposit(sc);
                request.put("action", "deposit");
                request.put("account", accountName);
                request.put("amount", money);
                out.println(request.toString());
            }

            case "statement" -> {
                JSONObject request = new JSONObject();
                request.put("action", "statement");
                out.println(request.toString());
            }

            case "exit" -> {
                System.out.println("Goodbye, and thank you for banking with us.");
                System.exit(0);
            }

            default -> printHelp();


        }
    }

    public long moneyToWithdraw(Scanner sc){
        System.out.println("How much do you want to withdraw?");
        long money = sc.nextInt();
        sc.nextLine();
        return money;
    }

    public long moneyToDeposit(Scanner sc){
        System.out.println("Enter the money to deposit.");
        long money = sc.nextInt();
        sc.nextLine();
        return money;
    }

}
