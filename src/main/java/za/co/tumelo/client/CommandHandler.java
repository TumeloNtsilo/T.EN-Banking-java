package za.co.tumelo.client;

import org.json.JSONObject;
import za.co.tumelo.Response;
import za.co.tumelo.command.*;

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


    public void handle(String command){
        if(!isValid(command)){
            printHelp();
            return;
        }

        switch (command){
            case "balance" -> {
                Command balance = new BalanceCommand(response);
                JSONObject result = balance.execute();
                System.out.println(result.toString());
            }

            case "withdraw" -> {
               Command withdraw = new WithdrawCommand(response);
               JSONObject result  = withdraw.execute();
                System.out.println(result);
            }

            case "deposit" -> {
                Command deposit = new DepositCommand("deposit");
                deposit.execute();
            }

            case "statement" -> {
                Command statement = new StatementCommand("statement");
                statement.execute();
            }

            case "exit" -> {
                System.out.println("Goodbye, and thank you for banking with us.");
                System.exit(0);
            }

            default -> printHelp();


        }
    }
}
