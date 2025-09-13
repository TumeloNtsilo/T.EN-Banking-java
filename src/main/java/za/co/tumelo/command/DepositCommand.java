package za.co.tumelo.command;

import org.json.JSONObject;
import za.co.tumelo.CreditAccount;
import za.co.tumelo.Response;
import za.co.tumelo.SavingsAccount;

import java.sql.SQLOutput;
import java.util.Scanner;

public class DepositCommand extends Command{
    private final Response response;


    public DepositCommand(Response response) {
        super("deposit");
        this.response = response;
    }

    @Override
    public JSONObject execute() {
        JSONObject message = new JSONObject();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the account you want to deposit to: (savings or credit)");
        String accountName = sc.nextLine();

        if(accountName.equalsIgnoreCase("credit")){
            System.out.println(response.getCreditAccount().viewBalance());
            System.out.println("Enter the money to deposit.");
            long money = sc.nextLong();

            if(money < 10){
                message.put("Credit", response.minimumAmountIs10());
            }else {
                response.getCreditAccount().deposit(money);
                message.put("Credit", response.successfulCreditDeposit());
            }


        }else if(accountName.equalsIgnoreCase("savings")){
            System.out.println(response.getSavingsAccount().viewBalance());
            System.out.println("Enter the money to deposit.");
            long money = sc.nextLong();

            if(money < 10){
                message.put("Credit", response.unsuccessfulSavingsDeposit());
            }else {
                response.getSavingsAccount().deposit(money);
                message.put("Credit", response.successfulSavingsDeposit());
            }

        }else {
            System.out.println("Enter the correct account name (savings or credit)");
        }


        return message;
    }
}
