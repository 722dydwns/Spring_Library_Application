package com.group.libraryapp.controller.user;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.dto.user.update.UserUpdateRequest;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //진입점 등록
public class UserController {
    private JdbcTemplate jdbcTemplate;
    //Service객체 주입 (V1-> V2) 변경
    private final UserServiceV2 userServiceV2;

    //생성자로 연결
    public UserController(UserServiceV2 userServiceV2){
        this.userServiceV2 = userServiceV2;
    }

    @PostMapping("/user") //리팫토링 시키기
    public void saveUser(@RequestBody UserCreateRequest request){
        userServiceV2.saveUser(request);
    }

    @GetMapping("/user") //기존 사용자 목록 밖으로 응답할 용도 API
    public List<UserResponse> getUsers(){
        return userServiceV2.getUsers();
    }
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userServiceV2.updateUsers(request);//여기서 호출만 함
    }
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userServiceV2.deleteUsers(name);
    }


}