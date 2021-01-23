package me.hongmo.querydsl.member.repo;

import me.hongmo.querydsl.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTset() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> all = memberJpaRepository.findAll();
        assertThat(all).containsExactly(member);

        List<Member> all2 = memberJpaRepository.findByUsername("member1");
        assertThat(all2).containsExactly(member);
    }

    @Test
    public void basicQuerydslTset() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        List<Member> all = memberJpaRepository.findAll_querydsl();
        assertThat(all).containsExactly(member);

        List<Member> all2 = memberJpaRepository.findByUsername_querydsl("member1");
        assertThat(all2).containsExactly(member);
    }

}