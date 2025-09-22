package za.co.tumelo.command;

import org.json.JSONObject;
import za.co.tumelo.Response;

import java.io.PrintWriter;
import java.util.Scanner;

public class DepositCommand extends Command{
    private final Response response;
    private final JSONObject request;


    public DepositCommand(Response response, JSONObject request) {
        super("deposit");
        this.response = response;
        this.request = request;
    }



    @Override
    public JSONObject execute(PrintWriter out) {
        JSONObject message = new JSONObject();

        String accountName = request.optString("account", "");
        long money = request.optLong("amount", 0);

        if(accountName.equalsIgnoreCase("credit")){
            out.println("\nBalance " + response.getCreditAccount().viewBalance());

            if(money < 10){
                message.put("Credit", response.minimumAmountIs10());
            }else {
                response.getCreditAccount().deposit(money);
                message.put("Credit", response.successfulCreditDeposit());
            }


        }else if(accountName.equalsIgnoreCase("savings")){
            out.println("\nBalance " + response.getSavingsAccount().viewBalance());

            if(money < 10){
                message.put("Credit", response.unsuccessfulSavingsDeposit());
            }else {
                response.getSavingsAccount().deposit(money);
                message.put("Credit", response.successfulSavingsDeposit());
            }

        }else {
            out.println("Enter the correct account name (savings or credit)");
        }

        return message;
    }
}
