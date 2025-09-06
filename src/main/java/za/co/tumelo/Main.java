package za.co.tumelo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to T.EN Bank");
        System.out.println("Where trust and the elegant of banking meet");
        System.out.println();
        System.out.println();
        System.out.println("Do you have an account? (y/n");

        String noAccount = sc.nextLine().trim();
        if (noAccount.equalsIgnoreCase("y") || noAccount.equalsIgnoreCase("yes")){
            System.out.println("Now let us open one for you.");
            CreateAccount createAccount = new CreateAccount();
            createAccount.enterDetails();
            createAccount.printPersonalDetails();
            System.out.println("Here is your pin number, use it login");
            System.out.println("Pin: " + createAccount.createPin());

        }else if(noAccount.equalsIgnoreCase("n") || noAccount.equalsIgnoreCase("no")){
            System.out.print("Please enter your pin to login: ");
            int pin = sc.nextInt();


        }



    }
}