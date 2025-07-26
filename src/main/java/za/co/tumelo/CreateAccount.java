package za.co.tumelo;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;

public class CreateAccount {
    private final Scanner sc;
    private final JSONObject personalDetails;

    public CreateAccount(){
        sc = new Scanner(System.in);
        personalDetails = new JSONObject();
    }

    public void enterDetails(){
        System.out.println("Please Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Please Enter your surname: ");
        String surname = sc.nextLine();
        System.out.println("Enter your year of birth: ");
        int yearOfBirth = sc.nextInt();
        System.out.println("Enter your month of birth(e.g February put 2: ");
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


        personalDetails.put("Name", name);
        personalDetails.put("Surname", surname);
        personalDetails.put("Date of birth", dateOfBirth.toString());
        personalDetails.put("Age", age);

    }

    public JSONObject getPersonalDetails() {
        return personalDetails;
    }
}
