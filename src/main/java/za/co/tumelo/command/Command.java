package za.co.tumelo.command;

import org.json.JSONObject;

public abstract class Command {
    private String name;

    public Command(String name){
        this.name = name;
    }

    public abstract JSONObject execute();

}
