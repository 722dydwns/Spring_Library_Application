package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity
@Table(name = "person") //매핑시킬 테이블 이름지정
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null; //추가 (테이블) PK, 자동 증가
    @Column(nullable = false, length = 20, name = "name")
    private String name;
    private Integer age;
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