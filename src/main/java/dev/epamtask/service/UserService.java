package dev.epamtask.service;

import dev.epamtask.exception.NotFoundException;
import dev.epamtask.model.Contact;
import dev.epamtask.model.User;
import dev.epamtask.parser.ParseDate;
import dev.epamtask.repository.UserRepository;
import dev.epamtask.util.NewUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    ContactService contactService;
    @Autowired
    private ApplicationContext context;


    public void getUserOptions() {
        int select;
        System.out.println(getAllCommands());
        do {
            System.out.print("Please enter Your select: ");
            select = new Scanner(System.in).nextInt();
            switch (select) {
                case 10:
                    updateUserById();
                    break;
                case 9:
                    deleteUserById(null);
                    break;
                case 8:
                    findById();
                    break;
                case 7:
                    createUser();
                    break;
                case 6:
                    findWithRequiredCity();
                    break;
                case 5:
                    findWithRequiredEmail();
                    break;
                case 4:
                    findWithRequiredPhoneNumber();
                    break;
                case 3:
                    findAllWithRequiredAge();
                    break;
                case 2:
                    findAllWithContact();
                    break;
                case 1:
                    findAll();
                    break;
                default:
                    break;
            }
        } while (select > 0);
        SpringApplication.exit(context);
    }

    private void findAll() {
        List<User> userList = userRepository.list();
        userList.forEach(user -> System.out.print(getPrint(user)));
    }

    private void findAllWithContact() {

        List<User> userList = userRepository.listWithContact();
        userList.forEach(user -> System.out.print(getPrintWithContact(user)));
    }

    private void findAllWithRequiredAge() {
        System.out.print("Enter required age: ");
        int age = new Scanner(System.in).nextInt();
        List<User> userList = userRepository.withRequiredAge(age);
        userList.forEach(user -> System.out.print(getPrintWithAge(user)));

    }

    private void findWithRequiredPhoneNumber() {
        System.out.print("Enter required phone Number: ");
        int phoneNumber = new Scanner(System.in).nextInt();
        List<User> userList = userRepository.withRequiredPhoneNumber(phoneNumber);
        userList.forEach(user -> System.out.print(getPrintWithContact(user)));

    }

    private void findWithRequiredEmail() {
        System.out.print("Enter required phone email: ");
        String email = new Scanner(System.in).next();
        List<User> userList = userRepository.withRequiredEmail(email);
        userList.forEach(user -> System.out.print(getPrintWithContact(user)));

    }

    private void findWithRequiredCity() {
        System.out.print("Enter required city: ");
        String city = new Scanner(System.in).next();
        List<User> userList = userRepository.withRequiredCity(city);
        userList.forEach(user -> System.out.print(getPrintWithContact(user)));

    }
     private void findById () {

         System.out.print("Enter ID: ");
         int id = new Scanner(System.in).nextInt();
         try {
             User user = userRepository.findById(id);
             System.out.println(getPrint(user));
         } catch (NotFoundException exception) {
             System.out.println(exception.toString());
         }

     }

     private void createUser() {
        User user = NewUser.getNewUser();
        Contact contact = user.getContact();

         int result = userRepository.create(user);
         try {
             if (result == 1) {
                 user.setUserId(userRepository.findByName(user.getFirstName(),user.getSecondName()).getUserId());
                 contact.setUser_id(user.getUserId());
                 log.info("New user created: " + user.getFirstName() + " " + user.getSecondName() + " with ID" + user.getUserId());
             } else {
                 throw new IllegalStateException("oops could not create contact");
             }
         } catch (IllegalStateException exception) {
             log.info(exception.toString());
         }

         contactService.createContact(contact);
     }
     private void deleteUserById(Integer id) {
        if (id == null) {
            System.out.print("Enter category id: ");
            id = new Scanner(System.in).nextInt();
        }
         try {
             int result = userRepository.delete(id);
             if (result != 1) {
                 throw new IllegalStateException("oops could not delete contact");
             } else log.info("Contact with id: " + id + " was deleted");
         } catch (IllegalStateException exception) {
             log.info(exception.toString());
         }
     }

     private void updateUserById() {

     }

    private static String getPrint(User user) {
        return String.format("%4d |%20s |%20s |\n",
                user.getUserId(),
                user.getFirstName(),
                user.getSecondName()
        );
    }
    private static String getPrintWithContact(User user) {

        Contact contact = user.getContact();
        return String.format("%4d |%20s |%20s |%20s |%20s |%20s |%20s |%20s |%20s |\n",
                user.getUserId(),
                user.getFirstName(),
                user.getSecondName(),
                contact.getCity(),
                contact.getEmail(),
                contact.getPhone_number(),
                contact.getAge(),
                contact.getDate_of_birth(),
                contact.getAddress()
        );
    }

    private static String getPrintWithAge(User user) {
        Contact contact = user.getContact();
        return String.format("%20s |%20s |%4d  |\n",
                user.getFirstName(),
                user.getSecondName(),
                contact.getAge()
        );
    }
    private static String getAllCommands() {
        return String.format("%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|\n",
                "\n  | ALL COMMANDS " ,
                "\n1.  | Find all users ",
                "\n2.  | Find all users with contact ",
                "\n3.  | Find all users with required age " ,
                "\n4.  | Find user with required phone number ",
                "\n5.  | Find user with required email ",
                "\n6.  | Find users with provided city ",
                "\n7.  | Create user " ,
                "\n8.  | Find user by ID ",
                "\n9.  | Delete user by ID ",
                "\n10. | Update user by ID ",
                "\n0.  | ---CLOSE application--- "
        );
    }

    public void createTestUser(User user) {
        Contact contact = user.getContact();

        int result = userRepository.create(user);
        try {
            if (result == 1) {
                user.setUserId(userRepository.findByName(user.getFirstName(),user.getSecondName()).getUserId());
                contact.setUser_id(user.getUserId());
                log.info("New user created: " + user.getFirstName() + " " + user.getSecondName() + " with ID" + user.getUserId());
            } else {
                throw new IllegalStateException("oops could not create contact");
            }
        } catch (IllegalStateException exception) {
            log.info(exception.toString());
        }

        contactService.createContact(contact);
    }
}
