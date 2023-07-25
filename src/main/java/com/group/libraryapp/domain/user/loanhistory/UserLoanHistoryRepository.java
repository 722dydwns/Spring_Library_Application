package com.group.libraryapp.domain.user.loanhistory;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//jpa 리포지토리 인터페이스
public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    //대출 여부 확인용 시그니처
    boolean existsByBookNameAndIsReturn(String bookName , boolean isReturn);

    //대출 기록 얻어오는 용도 - id와 책 이름
    Optional<UserLoanHistory> findByUserIdAndBookName(long userId, String bookName) ;

}
