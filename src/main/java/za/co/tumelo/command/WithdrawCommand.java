package za.co.tumelo.command;

import org.json.JSONObject;
import za.co.tumelo.Response;
import java.util.Scanner;

public class WithdrawCommand extends Command{
    private final Response response;

    public WithdrawCommand(Response response) {
        super("withdraw");
        this.response = response;
    }

    @Override
    public JSONObject execute() {
        JSONObject message = new JSONObject();
        Scanner sc = new Scanner(System.in);
        System.out.println("Withdraw from which account, credit or savings? (type credit or savings)");
        String accountName = sc.nextLine();



        if(accountName.equalsIgnoreCase("credit")){
            System.out.println("Balance " + response.getCreditAccount().viewBalance());
            System.out.println("Credit limit " + response.getCreditAccount().getCreditLimit());
            System.out.println("How much do you want to withdraw?");
            long money = sc.nextInt();
            long withdrawnMoney = response.getCreditAccount().withdraw(money);

            if(withdrawnMoney == money){
                System.out.println(money + " is being withdrawn");
                message.put("Credit account", response.successfulCreditWithdrawal());
            }else if (withdrawnMoney == 0){
                message.put("Credit account", response.unSuccessfulCreditWithdrawal());
            }

        }else if(accountName.equalsIgnoreCase("savings")){
            System.out.println("Balance " + response.getSavingsAccount().viewBalance());
            System.out.println("How much do you want to withdraw?");
            long money = sc.nextInt();
            long withdrawnMoney = response.getSavingsAccount().withdraw(money);

            if(withdrawnMoney == money){
                System.out.println(money + " is being withdrawn");
                message.put("Saving account", response.successfulSavingsWithdrawal());
            }else if(withdrawnMoney == 0){
                message.put("Savings account", response.unSuccessfulSavingsWithdrawal());
            }else if (money < 20){
                message.put("Saving account", response.minimumWithdrawalIs20());
            }
        }

        return message;
    }
}
