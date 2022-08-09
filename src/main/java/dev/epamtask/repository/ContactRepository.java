package dev.epamtask.repository;

import dev.epamtask.exception.NotFoundException;
import dev.epamtask.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Contact> rowMapper = (rs, rowNum) -> {
        Contact contact = new Contact();
        contact.setCity(rs.getString("city"));
        contact.setEmail(rs.getString("email"));
        contact.setPhone_number(rs.getInt("phone_number"));
        contact.setAge(rs.getInt("age"));
        contact.setDate_of_birth(rs.getDate("date_of_birth"));
        contact.setAddress(rs.getString("address"));
        return contact;
    };

    public Contact findById(int id) {
        String sql = "SELECT * FROM contacts WHERE user_id = ?";
        return jdbcTemplate.query(sql, rowMapper,id)
                .stream()
                .findFirst().orElseThrow(() -> new NotFoundException(String.format("Contact with id %s not found",id)));
    }


    public int create(Contact contact) {
        String sql = "INSERT into contacts(city,email,phone_number,age,date_of_birth,address,user_id) values(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,contact.getCity(),contact.getEmail(),contact.getPhone_number(),contact.getAge(),
                contact.getDate_of_birth(),contact.getAddress(),contact.getUser_id());
    }

    public int update(Contact contact, int id) {
        String sql = "UPDATE contacts set contact_id = ?,city = ?,email = ?,phone_number = ?" +
                ",age = ?,date_of_birth = ?,address = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql,contact.getCity(),contact.getEmail(),contact.getPhone_number(),contact.getAge(),
                contact.getDate_of_birth(),contact.getAddress(),id);
    }
}
