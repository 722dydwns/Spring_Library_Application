package com.group.libraryapp.domain.user.loanhistory;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {  //DB테이블에 매핑될 객체
    //대응될 필드 id, userID, bookname, is_retunn
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userId;

    private String bookName;

    private boolean isReturn;

    //기본 생성자 필요
    public UserLoanHistory() {
    }

    //생성자
    public UserLoanHistory(long userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn = isReturn;
    }

}
