package za.co.tumelo;

import org.json.JSONObject;

public class Response {
    private SavingsAccount savingsAccount = new SavingsAccount();
    private CreditAccount creditAccount = new CreditAccount(1000, 5000);

    public JSONObject savingsBalance(){
        JSONObject response = new JSONObject();
        response.put("balance", savingsAccount.viewBalance());

        return response;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public CreditAccount getCreditAccount() {
        return creditAccount;
    }

    public JSONObject creditAccountBalance(){
        JSONObject response = new JSONObject();
        response.put("balance", creditAccount.viewBalance());

        return response;
    }

    public JSONObject successfulSavingsWithdrawal(){
        JSONObject response = new JSONObject();
        response.put("result", "withdrawal successful");
        response.put("balance", savingsAccount.viewBalance());

        return response;
    }

    public JSONObject unSuccessfulSavingsWithdrawal(){
        JSONObject response = new JSONObject();
        response.put("result", "insufficient funds");
        response.put("balance", savingsAccount.viewBalance());

        return response;
    }

    public JSONObject minimumWithdrawalIs20(){
        JSONObject response = new JSONObject();
        response.put("result", "minimum amount to withdraw is 20");
        response.put("balance", savingsAccount.viewBalance());

        return response;
    }

    public JSONObject successfulCreditWithdrawal(){
        JSONObject response = new JSONObject();
        response.put("result", "withdrawal successful");
        response.put("balance", creditAccount.viewBalance());

        return response;
    }

    public JSONObject unSuccessfulCreditWithdrawal(){
        JSONObject response = new JSONObject();

        response.put("result", "insufficient funds, credit limit reached.");
        response.put("balance", creditAccount.viewBalance());

        return response;
    }


}
