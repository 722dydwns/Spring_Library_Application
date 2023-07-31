package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    //대출 여부 확인용 Repository 의존성 추가
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    //userId 가져와야 하기 때문에 의존성 추가
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository){
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    //책 대여
    @Transactional
    public void loanBook(BookLoanRequest request) {
        //여기서는 Repository 호출
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("이미 대출 중인 책입니다.");
        }
        //대출 기록 쌓기 위해 유저 정보, 책 이름 가져와서 만들어야 함
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        //정보로 대출 기록 저장 - 책 이름만 건내주면 됨
        user.loanBook(book.getName()); //이런 식으로 접근하여 저장
       // userLoanHistoryRepository.save(  new UserLoanHistory(user , book.getName()));
    }

    //도서 반납
    @Transactional
    public void returnBook(BookReturnRequest request){
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);

        System.out.println("Hello ");
        user.returnBook(request.getBookName()); //간단하게 반납처리 된다.
    }


}
