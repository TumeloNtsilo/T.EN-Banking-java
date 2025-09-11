package za.co.tumelo;

import org.json.JSONObject;

public class Response {

    public JSONObject savingsBalance(){
        JSONObject response = new JSONObject();
        SavingsAccount account = new SavingsAccount();

        response.put("balance", account.viewBalance());

        return response;
    }

    public JSONObject creditAccountBalance(){
        JSONObject response = new JSONObject();
        SavingsAccount account = new SavingsAccount();

        response.put("balance", account.viewBalance());

        return response;
    }
}
