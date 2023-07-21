package com.group.libraryapp.repository.user;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserJdbcRepository {
    //DB에 SQL 날리는 객체, 저장장치에 접근하는 클래스
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate){ //생성자로 주입
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExist(JdbcTemplate jdbcTemplate, long id){
        String sql = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum)-> 0, id).isEmpty();
    }

    public void updateUserName(JdbcTemplate jdbcTemplate, String name, long id){
        String sql = "UPDATE person SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    public void deleteUserByName(String name) {
        String sql = "DELETE FROM person WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public void saveUser(String name, Integer age) {
        String sql = "INSERT INTO person(name, age) VALUES(?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUserResponses() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql,  (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }
}
