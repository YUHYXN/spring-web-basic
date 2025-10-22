package com.codeit.springwebbasic.book.controller;

import com.codeit.springwebbasic.book.dto.request.BookCreateRequestDto;
import com.codeit.springwebbasic.book.dto.response.BookResponseDto;
import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor // final 변수를 초기화 하는 매개값을 전달받는 생성자.
public class BookController {

    private final BookService bookService;

    /*
    도서 등록
     {
        "title": string,
        "author": string,
        "isbn": string,
        "publisher": string,
        "publishedDate": date
     }
     */
    @RequestMapping(value = "/api/books", method = RequestMethod.POST)
    public BookResponseDto createBook(@Valid @RequestBody BookCreateRequestDto requestDto) {
        Book book = bookService.createBook(requestDto);
        return BookResponseDto.from(book);
    }

    // 조회 요청
    // url: localhost:8081/api/books/책id: GET
    // 메서드 이름: getBook
    @GetMapping("/api/books/{id}")
    public BookResponseDto getBook(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        return BookResponseDto.from(book);
    }


}
