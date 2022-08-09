package dev.epamtask.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "USERS")
public class User {
    @Id
    private Integer userId;
    private String firstName;
    private String secondName;
    @MappedCollection(idColumn = "USER_ID",keyColumn = "USER_ID")
    Contact contact;

    public User(){

    }

    public User(String firstName, String secondName, Contact contact) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.contact = contact;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userFirstName='" + firstName + '\'' +
                ", userSecondName='" + secondName + '\'' +
                ", contact=" + contact +
                '}';
    }
}
