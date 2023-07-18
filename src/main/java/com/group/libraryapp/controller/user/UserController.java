package com.group.libraryapp.controller.user;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.dto.user.update.UserUpdateRequest;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //진입점 등록
public class UserController {
    private JdbcTemplate jdbcTemplate;
    //Service객체 주입
    private final UserService userService;

    //생성자로 연결
    public UserController(UserService userService){
        this.userService = userService;
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