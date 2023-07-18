package com.group.libraryapp.domain.user;

public class User { //저장될 데이터 클래스 DAO
    private String name;
    private Integer age;
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
}