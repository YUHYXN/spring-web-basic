package com.codeit.springwebbasic.book.service;

import com.codeit.springwebbasic.book.dto.request.BookCreatRequestDto;
import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.book.entity.BookStatus;
import com.codeit.springwebbasic.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {


    private BookRepository bookRepository;

    public void createBook(BookCreatRequestDto requestDto) {
        // ISBN 중복 검사
        Optional<Book> byIsbn = bookRepository.findByIsbn(requestDto.getIsbn());
        if(byIsbn.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 ISBN입니다." + requestDto.getIsbn() ); // 에러 던지기
        }


        // AllArgsConstructor가 있어서 하나하나 생성자 만들 필요 없음
        // @Builder 어노테이션 덕분에 빌더 패턴으로 객체 생성 가능
        // Builder 패턴을 활용하면 초기화 순서를 내 마음대로 지정해도 상관 없고,

        Book book = Book.builder()
                .title(requestDto.getTitle())
                .author(requestDto.getAuthor())
                .isbn(requestDto.getIsbn())
                .publisher(requestDto.getPublisher())
                .publishedDate(requestDto.getPublishedDate())
                .status(BookStatus.AVAILABEL)
                .build();

        bookRepository.save();
    }
}
