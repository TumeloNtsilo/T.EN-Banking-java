package za.co.tumelo.command;

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class Command {
    private String name;

    public Command(String name){
        this.name = name;
    }

    public abstract JSONObject execute(PrintWriter out) throws IOException;

}
