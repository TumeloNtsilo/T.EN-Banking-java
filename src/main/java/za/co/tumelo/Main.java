package za.co.tumelo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to TEN-Bank...");
        System.out.println("Now let us open an account for you.");

        CreateAccount createAccount = new CreateAccount();
        createAccount.enterDetails();
        createAccount.printPersonalDetails();

        System.out.println("Here is your pin number, use it login");
        System.out.println("Pin: " + createAccount.createPin());

    }
}