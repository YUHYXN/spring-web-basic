package com.codeit.springwebbasic.rental.entity;

import com.codeit.springwebbasic.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class RentalRepository {

    private final Map<Long, Rental> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    // 대여 기록 저장
    public Rental save(Rental rental) {
        if (rental.getId() == null) {
            rental.setId(sequence.getAndIncrement());
        }
        store.put(rental.getId(), rental);
        return rental;
    }

    // ID로 대여 기록 조회
    public Optional<Rental> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 전체 대여 기록 조회
    public List<Rental> findAll() {
        return new ArrayList<>(store.values());
    }

    // 회원별 대여 기록 조회
    public List<Rental> findByMember(Member member) {
        return store.values().stream()
                .filter(rental -> rental.getMember().getId().equals(member.getId()))
                .collect(Collectors.toList());
    }

    // 대여 중인 대여 기록
    public List<Rental> findActiveRentals() {
        return store.values().stream()
                .filter(rental -> rental.getReturnedAt() == null)
                .collect(Collectors.toList());
    }

    // 반납 기한
    public List<Rental> findOverdueRentals() {
        return store.values().stream()
                .filter(rental -> rental.getReturnedAt() == null && rental.isOverdue())
                .collect(Collectors.toList());  // 반납이 안된 대여 기록 중에서 기한이 지난 것
    }

    // 회원별 대여 기록 수
    public long countByMember(Member member) {
        return store.values().stream()
                .filter(rental -> rental.getMember().getId().equals(member.getId()))
                .count();
    }

}
