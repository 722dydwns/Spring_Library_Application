package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JPA 연결한 Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String bookName);

}
