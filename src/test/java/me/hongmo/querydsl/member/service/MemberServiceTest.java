package me.hongmo.querydsl.member.service;

import me.hongmo.querydsl.authority.repo.AuthorityRepository;
import me.hongmo.querydsl.entity.Authority;
import me.hongmo.querydsl.member.repo.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {


    @PersistenceContext
    EntityManager em;

    @Autowired
    AuthorityRepository authorityRepository;

    @Test
    public void test() {
        final Authority role_user = authorityRepository.findByAuthorityName("ROLE_USER");
        Assertions.assertThat(role_user.getAuthorityName()).isEqualTo("ROLE_USER");
    }


}