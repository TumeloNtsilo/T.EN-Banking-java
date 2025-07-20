package za.co.tumelo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to TEN-Bank...");
        System.out.println("Do you have an account with us? (yes/no)");
        String answer = sc.nextLine();

        if(answer.equalsIgnoreCase("yes")){
            System.out.println("Okay, please enter you 4 digit pin: ");
            int pin = sc.nextInt();
        }else if(answer.equalsIgnoreCase("no")){
            System.out.println("Let's open one for you then.");
        }

    }
}