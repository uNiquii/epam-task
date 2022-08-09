package dev.epamtask.dao;

import dev.epamtask.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepositoryNew extends CrudRepository<User, Long> {

    @Query("SELECT USERS.USER_ID AS USER_ID, USERS.FIRST_NAME AS FIRST_NAME," +
            " USERS.SECOND_NAME AS SECOND_NAME, contact.AGE AS CONTACT_AGE, " +
            "contact.CITY AS CONTACT_CITY, contact.EMAIL AS CONTACT_EMAIL, contact.ADDRESS AS CONTACT_ADDRESS, " +
            "contact.USER_ID AS CONTACT_USER_ID, contact.PHONE_NUMBER AS CONTACT_PHONE_NUMBER, " +
            "contact.DATE_OF_BIRTH AS CONTACT_DATE_OF_BIRTH " +
            "FROM USERS LEFT OUTER JOIN CONTACTS contact ON contact.USER_ID = USERS.USER_ID " +
            "WHERE contact.AGE = :age")
    List<User> findByAge(@Param("age") Integer age);

    @Query("SELECT USERS.USER_ID AS USER_ID, USERS.FIRST_NAME AS FIRST_NAME," +
            " USERS.SECOND_NAME AS SECOND_NAME, contact.AGE AS CONTACT_AGE, " +
            "contact.CITY AS CONTACT_CITY, contact.EMAIL AS CONTACT_EMAIL, contact.ADDRESS AS CONTACT_ADDRESS, " +
            "contact.USER_ID AS CONTACT_USER_ID, contact.PHONE_NUMBER AS CONTACT_PHONE_NUMBER, " +
            "contact.DATE_OF_BIRTH AS CONTACT_DATE_OF_BIRTH " +
            "FROM USERS LEFT OUTER JOIN CONTACTS contact ON contact.USER_ID = USERS.USER_ID " +
            "WHERE contact.PHONE_NUMBER = :phoneNumber")
    List<User> findByPhoneNumber(@Param("phoneNumber") Integer phoneNumber);

    @Query("SELECT USERS.USER_ID AS USER_ID, USERS.FIRST_NAME AS FIRST_NAME," +
            " USERS.SECOND_NAME AS SECOND_NAME, contact.AGE AS CONTACT_AGE, " +
            "contact.CITY AS CONTACT_CITY, contact.EMAIL AS CONTACT_EMAIL, contact.ADDRESS AS CONTACT_ADDRESS, " +
            "contact.USER_ID AS CONTACT_USER_ID, contact.PHONE_NUMBER AS CONTACT_PHONE_NUMBER, " +
            "contact.DATE_OF_BIRTH AS CONTACT_DATE_OF_BIRTH " +
            "FROM USERS LEFT OUTER JOIN CONTACTS contact ON contact.USER_ID = USERS.USER_ID " +
            "WHERE contact.EMAIL = :email")
    List<User> findByEmail(@Param("email") String email);

    @Query("SELECT USERS.USER_ID AS USER_ID, USERS.FIRST_NAME AS FIRST_NAME," +
            " USERS.SECOND_NAME AS SECOND_NAME, contact.AGE AS CONTACT_AGE, " +
            "contact.CITY AS CONTACT_CITY, contact.EMAIL AS CONTACT_EMAIL, contact.ADDRESS AS CONTACT_ADDRESS, " +
            "contact.USER_ID AS CONTACT_USER_ID, contact.PHONE_NUMBER AS CONTACT_PHONE_NUMBER, " +
            "contact.DATE_OF_BIRTH AS CONTACT_DATE_OF_BIRTH " +
            "FROM USERS LEFT OUTER JOIN CONTACTS contact ON contact.USER_ID = USERS.USER_ID " +
            "WHERE contact.city = :city")
    List<User> findByCity(@Param("city") String city);

    @Query("SELECT * " +
            " FROM users ")
    List<User> findAllUsers();
}
