package com.group.libraryapp.dto.book.request;

//DTO
public class BookLoanRequest {
    private String userName;
    private String bookName;

    public String getBookName() {
        return bookName;
    }

    public String getUserName() {
        return userName;
    }
}
