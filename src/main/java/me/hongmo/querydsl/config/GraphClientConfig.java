package me.hongmo.querydsl.config;

import com.microsoft.graph.auth.confidentialClient.ClientCredentialProvider;
import com.microsoft.graph.auth.enums.NationalCloud;
import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Slf4j
@Configuration
public class GraphClientConfig {

    private final String clientId = "e4becb2b-3886-46c8-bb16-22a132f24a6c";
    private final String clientSecret = "Mwi8b1~5LBQbpUE3Cz9-BG_.yRA7s8bZ~3";
    private final String tenantId = "d630cdb0-fb53-41f8-ba22-03399ce5b046";

    @Bean
    public IGraphServiceClient getGraphClient(ClientCredentialProvider provider) {
        log.info("init graph client");
        DefaultLogger logger = new DefaultLogger();
        logger.setLoggingLevel(LoggerLevel.DEBUG);
        return GraphServiceClient.builder().authenticationProvider(provider).logger(logger)
                .buildClient();
    }

    @Bean
    public ClientCredentialProvider getClientCredentialProvider() {
        String defaultScope = "https://graph.microsoft.com/.default";

        return new ClientCredentialProvider(
                clientId,
                Collections.singletonList(defaultScope),
                clientSecret,
                tenantId,
                NationalCloud.Global);
    }

}
