package za.co.tumelo.command;

import org.json.JSONObject;

public class StatementCommand extends Command{
    public StatementCommand(String name) {
        super("withdraw");
    }

    @Override
    public JSONObject execute() {
        return null;
    }
}
