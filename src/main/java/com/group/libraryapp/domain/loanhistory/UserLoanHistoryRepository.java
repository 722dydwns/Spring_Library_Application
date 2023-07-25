package com.group.libraryapp.domain.loanhistory;


import com.group.libraryapp.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//jpa 리포지토리 인터페이스
public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    //대출 여부 확인용 시그니처
    boolean existsByBookNameAndIsReturn(String bookName , boolean isReturn);
}
