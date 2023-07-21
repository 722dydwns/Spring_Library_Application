package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //JPA Repository를 상속하는 것만으로 자동 빈 등록된다.
    //즉 @Repository 안붙여도 인식한다

    //여기서 name값 기준으로 User 얻도록 메소드 시그니처 만듬
    User findByName(String name);
}
