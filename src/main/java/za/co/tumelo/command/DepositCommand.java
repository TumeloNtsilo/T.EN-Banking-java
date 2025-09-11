package za.co.tumelo.command;

import org.json.JSONObject;

public class DepositCommand extends Command{
    public DepositCommand(String name) {
        super("deposit");
    }

    @Override
    public JSONObject execute() {
        return null;
    }
}
