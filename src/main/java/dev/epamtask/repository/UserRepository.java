package dev.epamtask.repository;

import dev.epamtask.dao.UserDao;
import dev.epamtask.exception.NotFoundException;
import dev.epamtask.model.Contact;
import dev.epamtask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements UserDao<User> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    ContactRepository contactRepository;


    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setSecondName(rs.getString("second_name"));
        return user;
    };

    RowMapper<User> rowMapperWithContact = (rs, rowNum) -> {
        User user = new User();
        Contact contact = new Contact();
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setSecondName(rs.getString("second_name"));
        contact.setCity(rs.getString("city"));
        contact.setEmail(rs.getString("email"));
        contact.setPhone_number(rs.getInt("phone_number"));
        contact.setAge(rs.getInt("age"));
        contact.setDate_of_birth(rs.getDate("date_of_birth"));
        contact.setAddress(rs.getString("address"));
        user.setContact(contact);
        return user;
    };


    @Override
    public List<User> list() {
        String sql = "SELECT * FROM users LIMIT 100";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public List<User> listWithContact() {
        String sql = "SELECT * " +
                " FROM users " +
                " JOIN contacts ON users.user_id = contacts.USER_ID ";
        return jdbcTemplate.query(sql,rowMapperWithContact);
    }

    @Override
    public List<User> withRequiredAge(Integer age) {
        String sql = "SELECT * " +
                " FROM users " +
                " JOIN contacts ON users.user_id = contacts.USER_ID " +
                " WHERE contacts.age = ?";
        return jdbcTemplate.query(sql,rowMapperWithContact,age);
    }

    @Override
    public List<User> withRequiredPhoneNumber(Integer phoneNumber) {
        String sql = "SELECT * " +
                " FROM users " +
                " JOIN contacts ON users.user_id = contacts.USER_ID " +
                " WHERE contacts.phone_number = ?";
        return jdbcTemplate.query(sql,rowMapperWithContact,phoneNumber);
    }

    @Override
    public List<User> withRequiredEmail(String email) {
        String sql = "SELECT * " +
                " FROM users " +
                " JOIN contacts ON users.user_id = contacts.USER_ID " +
                " WHERE contacts.email = ?";
        return jdbcTemplate.query(sql,rowMapperWithContact,email);
    }

    @Override
    public List<User> withRequiredCity(String city) {
        String sql = "SELECT * " +
                " FROM users " +
                " JOIN contacts ON users.user_id = contacts.USER_ID " +
                " WHERE contacts.city = ?";
        return jdbcTemplate.query(sql,rowMapperWithContact,city);
    }

    @Override
    public int create(User user) {
        String sql = "INSERT into users(first_name,second_name) values(?,?)";

        return jdbcTemplate.update(sql, user.getFirstName(), user.getSecondName());
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT user_id,first_name,second_name FROM users WHERE user_id = ?";
        User user = jdbcTemplate.query(sql, rowMapper,id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found",id)));
        return user;
    }
    public User findByName(String firstName, String secondName) {
        String sql = "SELECT user_id,first_name,second_name FROM users WHERE first_name = ? AND second_name = ?";
        User user = jdbcTemplate.query(sql, rowMapper,firstName,secondName)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("User with name %s %s not found",firstName,secondName)));
        return user;
    }

    @Override
    public int update(User user, int id) {
        String sql = "UPDATE users set first_name = ?, second_name = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql, user.getFirstName(), user.getSecondName(),id);


    }

    @Override
    public int delete(int id) {
        String sql = "DELETE from users WHERE user_id = ?";
        return jdbcTemplate.update(sql,id);

    }

}
