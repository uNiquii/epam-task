package dev.epamtask.dao;


import dev.epamtask.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao<T>  {

    List<T> list();

    List<T> listWithContact();

    List<T> withRequiredAge(Integer age);

    List<T> withRequiredPhoneNumber(Integer phoneNumber);

    List<T> withRequiredEmail(String email);

    List<T> withRequiredCity(String city);

    int create(T t);

    T findById(int id);

    int update(T t, int id);

    int delete(int id);

}
