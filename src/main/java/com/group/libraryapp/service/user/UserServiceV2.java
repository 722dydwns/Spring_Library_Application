package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.dto.user.update.UserUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 { //JPA 사용 서비스
    //userRepository 인터페이스
    private final UserRepository userRepository;

    //생성자로 주입받기
    public UserServiceV2(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //1) 저장기능 리팩토링
    //저장 메소드
    @Transactional
    public void saveUser(UserCreateRequest request) {
        //Entity 객체 생성하여 save처리
        User u = userRepository.save(new User(request.getName(), request.getAge()));
    }
    //2) 조회 기능 리팩토링
    @Transactional(readOnly = true) //단순 select 조회만 할 경우, readOnly 옵션을 쓴다.
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
    //3) 유저 업데이트 기능 리팩토링
    @Transactional
    public void updateUsers(UserUpdateRequest request){
        //id값 기준으로 데이터 탐색한 뒤,
        //탐색한 결과, User가 존재하면 얻고, 없으면 예외 던짐
        User user = userRepository.findById(request.getId()) //id값을 기준으로 얻어옴
                .orElseThrow(IllegalArgumentException::new);
        //API통해 들어온 이름 값으로 업데이트
        user.updateName(request.getName());
        //갱신한 이름을 다시 저장
        userRepository.save(user);
    }
    //4) 삭제 기능 리팩토링
    @Transactional
    public void deleteUsers(String name){ //이름 기준으로
        //널체크로
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        //이제 얻어온 User를 DB 상에서 삭제처리
        userRepository.delete(user);
    }
}