package za.co.tumelo.command;

import org.json.JSONObject;
import za.co.tumelo.Response;

import java.io.PrintWriter;
import java.util.Scanner;

public class DepositCommand extends Command{
    private final Response response;
    private final Scanner sc = new Scanner(System.in);


    public DepositCommand(Response response) {
        super("deposit");
        this.response = response;
    }

    public long moneyToDeposit(PrintWriter out){
        System.out.println("Enter the money to deposit.");
        return sc.nextLong();
    }

    @Override
    public JSONObject execute(PrintWriter out) {
        JSONObject message = new JSONObject();
        out.println("Enter the account you want to deposit to: (savings or credit)");
        String accountName = sc.nextLine();

        if(accountName.equalsIgnoreCase("credit")){
            out.println("\nBalance " + response.getCreditAccount().viewBalance());
            long money = moneyToDeposit(out);

            if(money < 10){
                message.put("Credit", response.minimumAmountIs10());
            }else {
                response.getCreditAccount().deposit(money);
                message.put("Credit", response.successfulCreditDeposit());
            }


        }else if(accountName.equalsIgnoreCase("savings")){
            out.println("\nBalance " + response.getSavingsAccount().viewBalance());
            long money = moneyToDeposit(out);

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
