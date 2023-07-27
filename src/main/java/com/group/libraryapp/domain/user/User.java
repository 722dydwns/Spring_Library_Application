package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person") //매핑시킬 테이블 이름지정
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null; //추가 (테이블) PK, 자동 증가
    @Column(nullable = false, length = 20, name = "name")
    private String name;
    private Integer age;

    //여러 개의 UserLoanHistory 갖고있고 싶음
    //주도권 없는 User 입장에서 mappedBy로 연관관게의 주도권 갖는 객체의 필드 이름을 명시해줘야 한다.
    @OneToMany (mappedBy = "user") //즉, UserLoanHistory 의 user 필드를 주도궈 있는 애로 명시 - 이 값이 명시되어야 진정한 데이터 저장 가능
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    //기본 생성자
    protected User(){}

    //생성자로 null검사
    public User(String name , Integer age){
        //검증
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }

        this.name = name;
        this.age = age;
    }
    //UserResponse 생성자에 담기 위해 getter 생성
    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return this.id;
    }

    public void updateName(String name){
        this.name = name;//변경하기
    }
}