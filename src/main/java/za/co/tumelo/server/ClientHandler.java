package za.co.tumelo.server;

import org.json.JSONObject;
import za.co.tumelo.Response;
import za.co.tumelo.command.BalanceCommand;
import za.co.tumelo.command.Command;
import za.co.tumelo.command.DepositCommand;
import za.co.tumelo.command.WithdrawCommand;
import za.co.tumelo.database.ClientDAO;
import za.co.tumelo.database.ClientsRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Long.parseLong;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private static BufferedReader in;
    private PrintWriter out;
    private final Response response = new Response();

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
    }



    @Override
    public void run() {
        try{
            String message;
            while((message = in.readLine()) != null){
                JSONObject request = new JSONObject(message);
                String action = request.optString("action", "");

                switch (action.toLowerCase().trim()){
                    case "login" -> login(request);
                    case "balance" -> handleBalance();
                    case "withdraw" -> handleWithdraw(request);
                    case "deposit" -> handleDeposit(request);
                    case "statement" -> handleStatement();
                    case "register" -> registerClient(request);
                    default -> sendMessage("Invalid command");
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void handleBalance() throws IOException {
        Command balance = new BalanceCommand(response);
        JSONObject result = balance.execute(out);
        sendMessage(result.toString());
    }

    public void handleDeposit(JSONObject request) throws IOException {
        Command deposit = new DepositCommand(response, request);
        JSONObject result = deposit.execute(out);
        sendMessage(result.toString());
    }

    public void handleWithdraw(JSONObject request) throws IOException {
        Command withdraw = new WithdrawCommand(response, request);
        JSONObject result = withdraw.execute(out);
        sendMessage(result.toString());
    }


    public void handleStatement(){
        //
    }

    public void sendMessage(String message){
        out.println(message);
        out.flush();
    }

    public void registerClient(JSONObject request){
        ClientsRepo.addClients(request.getString("Name(s)") + " " + request.getString("Surname"), request.getString("Date of birth"), request.getInt("pin"));
    }

    public void login(JSONObject request){
        int pin = request.getInt("pin");

        if(ClientDAO.isPinOkay(pin)){
            out.println("Pin is correct");
        }else {
            out.println("Pin incorrect\nTry again!");
        }
    }




}
