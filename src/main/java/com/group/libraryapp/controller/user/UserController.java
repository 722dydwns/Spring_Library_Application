package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController //진입점 등록
public class UserController {
    //얻은 데이터를 List에 저장할 거임
    private final List<User> users = new ArrayList<>();

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        User newUser = new User(request.getName() , request.getAge());//받은 데이터로 User 받고
        users.add(newUser);
    }

    @GetMapping("/user") //기존 사용자 목록 밖으로 응답할 용도 API
    public List<UserResponse> getUsers(){
        List<UserResponse> response = new ArrayList<>();
        //id 생성해야 하니까
        for(int i=0; i<users.size(); i++){ //List 순회하며 차례로 id값
            response.add( new UserResponse(i+1, users.get(i)));
        }
        return response; //객체 리스트로 반환했으므로 , 자동 JSON 객체르 변환 매핑되어 응답된다.
    }

}
