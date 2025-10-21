package com.codeit.springwebbasic.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   // Advice 전역 예외처리 어노테이션
public class CommonExceptionHandler {

    // Controller 단에서 발생하는 모든 예외를 일괄 처리하는 클래스
    // 실제 예외는 Service 계층에서 대부분 발생하지만, 따로 예외 처리가 없는 경우
    // 메서드를 호출한 상위 계층으로 전파됩니다.

    @ExceptionHandler(IllegalArgumentException.class)   // 예외를 다룬다
    public ResponseEntity<String> illegalArgsHandler(IllegalArgumentException e) {    // 도서를 찾을 수 없습니다 예외 처리가 여기 e로 온다
        e.printStackTrace();
        // 예외 원인을 http 상태 코드와 메세지를 통해 알려주고 싶다 -> ResponseEntity(응답용 Entity 객체)
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }

    // 미처 준비하지 못한 타입의 예외가 발생했을 시 처리할 메서드
    // 이걸 선언 안 하면 내가 준비하지 못한 예외는 터진다.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
