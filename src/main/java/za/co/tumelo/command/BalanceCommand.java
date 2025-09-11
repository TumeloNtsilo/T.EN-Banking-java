package za.co.tumelo.command;

import org.json.JSONObject;
import za.co.tumelo.Response;

public class BalanceCommand extends Command{
    public BalanceCommand(String name) {
        super("balance");
    }

    @Override
    public JSONObject execute() {
        JSONObject message = new JSONObject();
        Response response = new Response();

        message.put("Savings account", response.savingsBalance());

        message.put("Credit account", response.creditAccountBalance());


        return message;
    }
}
