package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.User;

public class UserResponse { //응답될 객체 DTO
    private long id;
    private String name;
    private Integer age;

    public UserResponse(long id, User user) { //id식별, User(DAO) 로 저장
        this.id = id;
        this.name = user.getName();
        this.age = user.getAge();
    }

    public UserResponse(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}