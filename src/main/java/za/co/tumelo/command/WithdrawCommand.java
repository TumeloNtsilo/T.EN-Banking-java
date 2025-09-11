package za.co.tumelo.command;

import org.json.JSONObject;

public class WithdrawCommand extends Command{

    public WithdrawCommand(String name) {
        super("withdraw");
    }

    @Override
    public JSONObject execute() {
        return null;
    }
}
