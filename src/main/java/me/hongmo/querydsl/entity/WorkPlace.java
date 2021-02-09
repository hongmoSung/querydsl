package me.hongmo.querydsl.entity;

import javax.persistence.*;

@Entity
public class WorkPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workPlaceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foor_id")
    private Floor floor;
}
