package me.hongmo.querydsl.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.jdo.annotations.Unique;
import javax.persistence.*;
import java.time.LocalDateTime;
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
    @CreationTimestamp
    private LocalDateTime createDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "authority")
    List<Member> members = new ArrayList<>();
}
