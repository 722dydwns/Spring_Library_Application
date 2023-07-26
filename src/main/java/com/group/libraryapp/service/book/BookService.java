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

        //정보로 대출 기록 저장
        userLoanHistoryRepository.save(  new UserLoanHistory(user.getId(), book.getName()));
    }

    //도서 반납
    @Transactional
    public void returnBook(BookReturnRequest request){
        //1) user이름으로 id 얻어오기 위해 User객체 얻어오기
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        //2) 대출 기록을 찾아와야 함 -> 거기 부분을 변경할 거라
        // 우선 대출 기록 얻으려면 Repository에서 관련 sql 함수 추기
        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        //3) 얻은 해당 대출 기록에 대한 isReturn 값을 true로 변환해주는 함수 호출
        history.doReturn();
        //4) 바뀐 객체데이터를 다시 대출 기록에 저장
        userLoanHistoryRepository.save(history); //트랜잭션이 알아서 변경 감지하여 저장해주기 때문에 빼도 된다.
    }


}
