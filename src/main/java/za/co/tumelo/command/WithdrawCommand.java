package za.co.tumelo.command;

import org.json.JSONObject;
import za.co.tumelo.Response;
import za.co.tumelo.server.ClientHandler;

import java.io.IOException;
import java.io.PrintWriter;

public class WithdrawCommand extends Command{
    private final Response response;
    private final JSONObject request;


    public WithdrawCommand(Response response, JSONObject request) {
        super("withdraw");
        this.response = response;
        this.request = request;
    }


    @Override
    public JSONObject execute(PrintWriter out) throws IOException {
        JSONObject message = new JSONObject();

        String accountName = request.optString("account", "");
        long money = request.optLong("amount", 0);

        if(accountName.equalsIgnoreCase("credit")){
            out.println("\nBalance " + response.getCreditAccount().viewBalance());
            out.println("Credit limit " + response.getCreditAccount().getCreditLimit());


            long withdrawnMoney = response.getCreditAccount().withdraw(money);

            if(withdrawnMoney == money){
                out.println(money + " is being withdrawn");
                message.put("Credit account", response.successfulCreditWithdrawal());
            }else if (withdrawnMoney == 0){
                message.put("Credit account", response.unSuccessfulCreditWithdrawal());
            }

        }else if(accountName.equalsIgnoreCase("savings")){
            out.println("\nBalance " + response.getSavingsAccount().viewBalance());

            long withdrawnMoney = response.getSavingsAccount().withdraw(money);

            if (money < 20){
                message.put("Saving account", response.minimumWithdrawalIs20());
            }else if(withdrawnMoney == money){
                out.println(money + " is being withdrawn");
                message.put("Saving account", response.successfulSavingsWithdrawal());
            }else if(withdrawnMoney == 0){
                message.put("Savings account", response.unSuccessfulSavingsWithdrawal());
            }
        }else {
            out.println("Enter the correct account name (savings or credit");
        }

        return message;
    }
}
