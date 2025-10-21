package com.codeit.springwebbasic.book.controller;

import com.codeit.springwebbasic.book.dto.request.BookCreatRequestDto;
import com.codeit.springwebbasic.book.dto.response.BookResponseDto;
import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /*
     * {
     *   "title": string,
     *   "author": string,
     *   "isbn": string,
     *   "publisher": string,
     *   "publishedDate": date (= LocalDate),
     * }
     *
     * */
    public BookResponseDto createBook(@Valid @RequestBody BookCreatRequestDto requestDto) {

        Book book = bookService.createBook(requestDto);
        return BookResponseDto.from(book);

    }

}
