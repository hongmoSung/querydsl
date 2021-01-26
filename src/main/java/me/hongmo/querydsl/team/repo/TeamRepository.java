package me.hongmo.querydsl.team.repo;

import me.hongmo.querydsl.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
