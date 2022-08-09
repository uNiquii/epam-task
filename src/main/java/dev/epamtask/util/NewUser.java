package dev.epamtask.util;

import dev.epamtask.model.Contact;
import dev.epamtask.model.User;
import dev.epamtask.parser.ParseDate;

import java.util.Scanner;

public class NewUser {
    public static User getNewUser() {
        Contact contact = new Contact();
        System.out.print("Enter user first name: ");
        String firstName = new Scanner(System.in).next();
        System.out.print("Enter user second name: ");
        String secondName = new Scanner(System.in).next();

        User user = new User(firstName, secondName, contact);

        System.out.print("Would you like to create also contact informations now? ");
        System.out.println("\n1.  | Create contact " +
                "\n2.  | Create it later ");
        int select = new Scanner(System.in).nextInt();

        if (select == 1) {
            System.out.print("Enter city: ");
            String city = new Scanner(System.in).next();
            System.out.print("Enter email: ");
            String email = new Scanner(System.in).next();
            System.out.print("Enter phone number: ");
            Integer phoneNumber = new Scanner(System.in).nextInt();
            System.out.print("Enter age: ");
            Integer age = new Scanner(System.in).nextInt();
            System.out.print("Enter date of birthday: ");
            String dateOfBirth = new Scanner(System.in).next();
            System.out.print("Enter  address: ");
            String address = new Scanner(System.in).next();
            contact.setCity(city);
            contact.setEmail(email);
            contact.setPhone_number(phoneNumber);
            contact.setAge(age);
            contact.setDate_of_birth(ParseDate.parseDate(dateOfBirth));
            contact.setAddress(address);
        }
        user.setContact(contact);
        return user;
    }
}
