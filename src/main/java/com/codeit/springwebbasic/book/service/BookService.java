package com.codeit.springwebbasic.book.service;

import com.codeit.springwebbasic.book.dto.request.BookCreateRequestDto;
import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.book.entity.BookStatus;
import com.codeit.springwebbasic.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(BookCreateRequestDto requestDto) {
        // ISBN 중복 체크
        Optional<Book> byIsbn = bookRepository.findByIsbn(requestDto.getIsbn());
        if (byIsbn.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 ISBN입니다: " + requestDto.getIsbn());
        }

        // Builder 패턴을 활용하면 초기화 순서를 내 맘대로 지정해도 상관 없고,
        // 원하는 필드만 골라서 초기화 하는것이 가능.
        Book book = Book.builder()
                .status(BookStatus.AVAILABLE)
                .author(requestDto.getAuthor())
                .isbn(requestDto.getIsbn())
                .title(requestDto.getTitle())
                .publisher(requestDto.getPublisher())
                .publishedDate(requestDto.getPublishedDate())
                .build();

        return bookRepository.save(book);
    }

    public Book getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("도서를 찾을 수 없습니다."));

        return book;
    }
}
