package za.co.tumelo;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.Scanner;

public class CreateAccount {
    private final Scanner sc;
    private final JSONObject personalDetails;

    public CreateAccount(){
        sc = new Scanner(System.in);
        personalDetails = new JSONObject();
    }

    public void enterDetails(){
        while(true){
            try{
                System.out.println("Please Enter your first name and your middle name with space in between: ");
                String name = sc.nextLine().trim();
                if(name.isEmpty()) throw new IllegalArgumentException();
                String[] names = name.split(" ");
                System.out.println(names[0]);
                System.out.println(names[1]);

                System.out.println("Please Enter your second name: ");
                String surname = sc.nextLine().trim();
                if(surname.isEmpty()) throw new IllegalArgumentException();

                System.out.println("Enter your year of birth: ");
                int yearOfBirth = sc.nextInt();

                System.out.println("Enter your month of birth(e.g February put 2): ");
                int monthOfBirth = sc.nextInt();

                System.out.println("Enter your day of birth (e.g 7): ");
                int dayOfBirth = sc.nextInt();

                LocalDate dateOfBirth;
                try{
                    dateOfBirth = LocalDate.of(yearOfBirth,monthOfBirth, dayOfBirth);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
                if(age < 18){
                    System.out.println("Sorry " + names[0] + " you are too young to open any account with us!");
                    System.out.println("Exiting, please enjoy the rest of your day.");
                    System.exit(0);
                }

                personalDetails.put("Name(s)", name);
                personalDetails.put("Surname", surname);
                personalDetails.put("Date of birth", dateOfBirth.toString());
                personalDetails.put("Age", age);
                break;
            } catch (RuntimeException e) {
                System.out.println("Invalid inputs");
                sc.nextLine();
                System.out.println("Let us try again!");
            }

        }

    }

    public JSONObject getPersonalDetails() {
        return personalDetails;
    }

    public void printPersonalDetails(){
        System.out.println(this.personalDetails);
    }

    public int createPin(){
        Random rd = new Random();
        return rd.nextInt(9000) + 1000;
    }
}
