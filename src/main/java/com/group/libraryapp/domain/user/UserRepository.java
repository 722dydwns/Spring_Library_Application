package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //JPA Repository를 상속하는 것만으로 자동 빈 등록된다.
    //즉 @Repository 안붙여도 인식한다

}
