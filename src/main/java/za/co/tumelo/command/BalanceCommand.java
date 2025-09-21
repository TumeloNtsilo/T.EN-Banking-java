package za.co.tumelo.command;

import org.json.JSONObject;
import za.co.tumelo.Response;

import java.io.PrintWriter;

public class BalanceCommand extends Command{
    private final Response response;
    public BalanceCommand(Response response) {
        super("balance");
        this.response = response;
    }

    @Override
    public JSONObject execute(PrintWriter out) {
        JSONObject message = new JSONObject();
        message.put("Savings account", response.savingsBalance());
        message.put("Credit account", response.creditAccountBalance());


        return message;
    }
}
