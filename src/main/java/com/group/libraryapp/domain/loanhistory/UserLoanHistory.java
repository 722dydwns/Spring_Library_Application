package com.group.libraryapp.domain.loanhistory;
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

    public UserLoanHistory() {
    }

    //생성자
    public UserLoanHistory(Long id, String name) {
        this.id = id;
        this.bookName = name;
    }

}
