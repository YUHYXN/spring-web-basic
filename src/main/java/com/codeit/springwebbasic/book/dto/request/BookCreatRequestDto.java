package com.codeit.springwebbasic.book.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// DTO : Data Transfer Object
// 비즈니스 로직 없이 순수하게 전달하고 싶은 데이터를 담는 용도로 사용하는 객체
// 요청 DTO, 응답 DTO로 나누어 사용한다 / 꼭 필요한 데이터만 정제해서 전달하는 용도로 사용한다.
// Entity를 직접 사용하지 않는 이유는
// 1. 필요없는 정보까지 노출 될 위험, 2. 도메인이 바뀌면 다른 스펙도 전부 변경되어야 하는 문제 발생
// 3. 검증 로직 배치의 애매함 등이 있다.
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookCreatRequestDto {

    /*
        @NotNull : null 값만 체크 (빈 문자열이나 공백은 허용)
        @NotEmpty : null 값 + 빈 문자열 체크 (공백은 허용)
        @NotBlank : null 값 + 빈 문자열 + 공백 체크 (전부 허용하지 않음)
    */


    @NotBlank(message = "제목은 필수입니다.")
//    @Size(max = 10, message = "제목은 최대 10자까지 가능합니다.")
    private String title;

    @NotBlank(message = "저자는 필수입니다.")
    private String author;

    @NotBlank(message = "ISBN은 필수입니다.")
    // 정규표현식 (Regular Expression) : reg exp = regular expression 문자열의 일정한 패턴을 표현하는 형식 언어.
    // 예를 들어 \d는 숫자를 의미, {13}은 13자리라는 의미
    @Pattern(regexp = "\\d{13}", message = "ISBN은 13자리 숫자여야 합니다.")
    private String isbn;

    @NotBlank(message = "출판사는 필수입니다.")
    private String publisher;

    @PastOrPresent(message = "출판일은 오늘 날짜이거나 과거여야 합니다.")
    private LocalDate publishedDate;



}
