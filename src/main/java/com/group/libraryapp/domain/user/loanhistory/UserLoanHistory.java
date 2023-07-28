package com.group.libraryapp.domain.user.loanhistory;
import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {  //DB테이블에 매핑될 객체
    //대응될 필드 id, userID, bookname, is_retunn
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private long userId;
    @ManyToOne //연관관계 맺게 하기
    private User user;

    private String bookName;

    private boolean isReturn;

    //기본 생성자 필요
    public UserLoanHistory() {
    }

    //생성자
    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    //반납 처리
    public void doReturn(){
        this.isReturn = true;//이제 반납 처리
    }

    public String getBookName() {
        return bookName;
    }
}
