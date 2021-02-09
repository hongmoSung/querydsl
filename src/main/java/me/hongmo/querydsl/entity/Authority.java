package me.hongmo.querydsl.entity;

import lombok.*;

import javax.jdo.annotations.Unique;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;
    @Column(name = "authority_name", nullable = false)
    private String authorityName;

    @OneToMany(mappedBy = "authority")
    List<Member> members = new ArrayList<>();
}
