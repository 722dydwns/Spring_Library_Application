package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
//JPA 연결한 Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
