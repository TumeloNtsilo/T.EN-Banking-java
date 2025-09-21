package za.co.tumelo.command;

import org.json.JSONObject;

import java.io.PrintWriter;

public class StatementCommand extends Command{
    public StatementCommand(String name) {
        super("withdraw");
    }

    @Override
    public JSONObject execute(PrintWriter out) {
        return null;
    }
}
