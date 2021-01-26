package me.hongmo.querydsl.member.repo;

import me.hongmo.querydsl.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    List<Member> findByUsername(String username);

    Optional<Member> findById(Long Id);

}
