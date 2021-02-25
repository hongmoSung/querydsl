package me.hongmo.querydsl.config;

import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;
import lombok.RequiredArgsConstructor;
import me.hongmo.querydsl.entity.Authority;
import me.hongmo.querydsl.entity.Member;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init() {
        initMemberService.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitMemberService {

        private final IGraphServiceClient graphServiceClient;

        private final PasswordEncoder passwordEncoder;

        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {
            Authority role_user = Authority.builder()
                    .authorityName("ROLE_USER")
                    .build();
            em.persist(role_user);

            List<User> users = graphServiceClient
                    .users()
                    .buildRequest()
                    .get()
                    .getCurrentPage();

            users.stream().forEach(user -> {
                Member build = Member.builder()
                        .displayName(user.displayName)
                        .mail(user.userPrincipalName)
                        .aadid(user.id)
                        .userPrincipalName(user.userPrincipalName)
                        .activated(true)
                        .authority(role_user)
                        .password(passwordEncoder.encode(user.userPrincipalName))
                        .build();
                em.persist(build);
            });

        }
    }

}
