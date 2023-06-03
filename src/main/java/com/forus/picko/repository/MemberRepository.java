package com.forus.picko.repository;

import com.forus.picko.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByKakaoId(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
