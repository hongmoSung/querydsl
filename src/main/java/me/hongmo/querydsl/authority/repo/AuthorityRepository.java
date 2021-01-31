package me.hongmo.querydsl.authority.repo;

import me.hongmo.querydsl.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByAuthorityName(String authorityName);
}
