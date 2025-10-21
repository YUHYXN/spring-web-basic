package com.codeit.springwebbasic.rental.entity;

import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.member.entity.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Rental {

    private long id;
    private Member member;
    private Book book;
    private LocalDateTime rentedAt;     // 대여한 날/시간
    private LocalDateTime dueDate;          // 반납 예정일
    private LocalDateTime returnedAt;   // 반납한 날/시간


    public void returnBook() {
        if (this.rentedAt == null) {
            throw new IllegalStateException("이미 반납된 도서입니다.");
        }

        this.returnedAt = LocalDateTime.now();  // 현재 날짜, 시간 정보
        this.book.returnBook(); // book의 status를 AVAILABEL로 변경

    }

    // 기한이 지났는지를 체크
    public boolean usOverdue() {
        // dueDate랑 returnedAt 비교해서 연체가 됐는지 체크
        // 반납이 되었다면 반납된 날짜로 체크, 아직 반납이 안 되었다면 현재 날짜로 체크
        LocalDateTime checkDate = returnedAt != null ? returnedAt : LocalDateTime.now();    // return 됐으면 returnedAt, 안됐으면 현재 시간
        return checkDate.isAfter(this.dueDate); // checkDate가 dueDate보다 이후인지 체크(이후면 ture, 아니면 false)
    }

}
