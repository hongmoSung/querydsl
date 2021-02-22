package me.hongmo.querydsl.config;

import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;
import lombok.RequiredArgsConstructor;
import me.hongmo.querydsl.entity.Member;
import org.springframework.context.annotation.Profile;
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
        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {
            List<User> users = graphServiceClient
                    .users()
                    .buildRequest()
                    .get()
                    .getCurrentPage();

            users.stream().forEach(user -> {
                Member build = Member.builder()
                        .displayName(user.displayName)
                        .mail(user.mail)
                        .aadid(user.id)
                        .userPrincipalName(user.userPrincipalName)
                        .activated(true)
                        .build();
                em.persist(build);
            });
        }
    }

}
