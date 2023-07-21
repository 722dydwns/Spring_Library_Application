package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.dto.user.update.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceV1 {
    //얘는 UserRepository를 사용
    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(JdbcTemplate jdbcTemplate){
        this.userJdbcRepository = new UserJdbcRepository(jdbcTemplate);
    }

    //실질적인 비즈니스 로직 담당 -Controller에 묶여있던 기능들을
    //동일한 이름의 메소드 안에 담당하도록 옮겨주고, 기존 컨트롤러에서는 이 Service의 함수를 호출만 하도록 하자

    public void updateUser(JdbcTemplate jdbcTemplate, UserUpdateRequest request){
        String readSql = "SELECT * FROM person WHERE id = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
        if(isUserNotExist){
            throw new IllegalArgumentException();//예외 던지기
        }
        //sql자겅하여 직접 DB에 접근하는 UserRepository객체의 함수를 여기서 호출
        userJdbcRepository.updateUserName(jdbcTemplate, request.getName(), request.getId());
    }
    //삭제 메소드
    public void deleteUser(String name) {

        userJdbcRepository.deleteUserByName(name);
    }
    //저장 메소드
    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }
    //조회 메소드
    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUserResponses();
    }
}
