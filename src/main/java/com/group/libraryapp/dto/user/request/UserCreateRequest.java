package com.group.libraryapp.dto.user.request;

public class UserCreateRequest { //JSON이 매핑될 객체 DTO
    //데이터
    private String name;
    private Integer age;//null가능하므로

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}