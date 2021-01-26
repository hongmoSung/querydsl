//package me.hongmo.querydsl.member.repo;
//
//import me.hongmo.querydsl.entity.Member;
//import me.hongmo.querydsl.entity.Team;
//import me.hongmo.querydsl.member.dto.MemberSearchDto;
//import me.hongmo.querydsl.member.dto.MemberTeamDto;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class MemberJpaRepositoryTest {
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Autowired
//    MemberJpaRepository memberJpaRepository;
//
//    @Test
//    public void basicTset() {
//        Member member = new Member("member1", 10);
//        memberJpaRepository.save(member);
//
//        Member findMember = memberJpaRepository.findById(member.getId()).get();
//        assertThat(findMember).isEqualTo(member);
//
//        List<Member> all = memberJpaRepository.findAll();
//        assertThat(all).containsExactly(member);
//
//        List<Member> all2 = memberJpaRepository.findByUsername("member1");
//        assertThat(all2).containsExactly(member);
//    }
//
//    @Test
//    public void basicQuerydslTset() {
//        Member member = new Member("member1", 10);
//        memberJpaRepository.save(member);
//
//        List<Member> all = memberJpaRepository.findAll_querydsl();
//        assertThat(all).containsExactly(member);
//
//        List<Member> all2 = memberJpaRepository.findByUsername_querydsl("member1");
//        assertThat(all2).containsExactly(member);
//    }
//
//    @Test
//    public void searchTest() {
//        Team teamA = new Team("teamA");
//        Team teamB = new Team("teamB");
//
//        em.persist(teamA);
//        em.persist(teamB);
//
//        Member member1 = new Member("member1", 10, teamA);
//        Member member2 = new Member("member2", 20, teamA);
//        Member member3 = new Member("member3", 30, teamB);
//        Member member4 = new Member("member4", 40, teamB);
//
//        em.persist(member1);
//        em.persist(member2);
//        em.persist(member3);
//        em.persist(member4);
//
//        final MemberSearchDto conditon = new MemberSearchDto();
//        conditon.setTeamName("teamB");
//        conditon.setAgeGoe(35);
//        conditon.setAgeLoe(50);
//
//        final List<MemberTeamDto> searchByBuilder = memberJpaRepository.searchByBuilder(conditon);
//
//        assertThat(searchByBuilder).extracting("username").isEqualTo("member4");
//    }
//
//}