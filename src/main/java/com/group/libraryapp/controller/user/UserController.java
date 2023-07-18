package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController //진입점 등록
public class UserController {

    //DB연결을 위해
    private final JdbcTemplate jdbcTemplate;
    //생성자로 연결
    public UserController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;//여기서 연결
    }

    @PostMapping("/user") //리팫토링 시키기
    public void saveUser(@RequestBody UserCreateRequest request){
       //1) sql 문 작성
       String sql = "INSERT INTO person(name, age) VALUES(?,?)";
        //2) jdbvTemplate 에 보냄
        jdbcTemplate.update(sql, request.getName(), request.getAge());//?에 담길 데이터를 request에 접근하여 저장
    }

    @GetMapping("/user") //기존 사용자 목록 밖으로 응답할 용도 API
    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>(){
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                //쿼리 결과는 ResultSet 안에 모두 담겨 있다. rs로 접근하여 뷰로 보낼 데이터를 얻고, 반환용 객체에 세팅해주면 된다.
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age); // 반환용 객체
            }
        });
    }
}