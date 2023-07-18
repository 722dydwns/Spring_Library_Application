package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.dto.user.update.UserUpdateRequest;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController //진입점 등록
public class UserController {

    //DB연결을 위해
    private final JdbcTemplate jdbcTemplate;
    //Service객체 주입
    private final UserService userService;

    //생성자로 연결
    public UserController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;//여기서 연결
        this.userService = new UserService(jdbcTemplate);
    }

    @PostMapping("/user") //리팫토링 시키기
    public void saveUser(@RequestBody UserCreateRequest request){
       userService.saveUser(request);
    }

    @GetMapping("/user") //기존 사용자 목록 밖으로 응답할 용도 API
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(jdbcTemplate, request);//여기서 호출만 함
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userService.deleteUser(name);
    }


}