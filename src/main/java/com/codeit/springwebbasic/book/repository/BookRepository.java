package com.codeit.springwebbasic.book.repository;

import com.codeit.springwebbasic.book.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    // 웹 어플리케이션은 동시에 여러 요청이 한꺼번에 들어올 수 있기 때문에
    // 멀티 스레드에서도 안전하게 사용할 수 있는 HashMap을 사용하는 것이다.
    // AtomicLong도 마찬가지로 Long 타입의 정수를 멀티 스레드에서 안전하게 공유 가능.
    private final Map<Long, Book> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    // 도서 저장
    public Book save(Book book) {
        if(book.getId() == null){
            book.setId(sequence.getAndIncrement()); // 값을 얻고난 후 값을 하나 증가
        }
        store.put(book.getId(), book);
        return book;
    }

    // 도서 조회
    public List<Book> findAll() {
        // Map에서 Value들만 전부 꺼낸 후 List로 변환
        return new ArrayList<>(store.values());
    }


    // id에 따라 조회가 안 될 수도 있잖아 -> 조회가 안 되면 NUll 리턴 -> NPE 발생 가능성 높음
    // Optional로 포장해서 호출한 쪽에서 충분한 검증 후에 Book 개체를 사용하도록 유도.
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }   // 옵셔널이 있어서 굳이 내가 if 문으로 null 체크를 하지 않아도 된다.


    // ISBN으로 조회
    public Optional<Book> findByIsbn(String isbn) {
        return store.values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }


    // 메서드 선언할 때 리턴 타입 일단 잘 모르겠으면 void
    public List<Book> findByTitleContaining(String title) {   // 해당 title이 포함된 도서만 조회
        return store.values().stream()
                .filter(book -> book.getTitle().contains(title))
                .collect(Collectors.toList());

    }


}
