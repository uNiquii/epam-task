package dev.epamtask.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("CONTACTS")
public class Contact {
    @Id
    private int user_id;
    private String city;
    private String email;
    private Integer phone_number;
    private Integer age;
    private Date date_of_birth;
    private String address;

    public Contact() {
    }
    public Contact(int user_id,String city, String email, Integer phone_number, Integer age, Date date_of_birth, String address) {
        this.user_id = user_id;
        this.city = city;
        this.email = email;
        this.phone_number = phone_number;
        this.age = age;
        this.date_of_birth = date_of_birth;
        this.address = address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", phone_number=" + phone_number +
                ", age=" + age +
                ", date_of_birth=" + date_of_birth +
                ", address='" + address + '\'' +
                '}';
    }
}
