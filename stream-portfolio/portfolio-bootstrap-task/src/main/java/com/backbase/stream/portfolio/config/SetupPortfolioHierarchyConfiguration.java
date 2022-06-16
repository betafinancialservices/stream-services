package com.backbase.stream.portfolio.config;

import com.backbase.stream.portfolio.PortfolioSaga;
import com.backbase.stream.portfolio.PortfolioTask;
import com.backbase.stream.portfolio.model.WealthBundle;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@EnableTask
@Configuration
@AllArgsConstructor
@Slf4j
@EnableConfigurationProperties(BootstrapConfigurationProperties.class)
public class SetupPortfolioHierarchyConfiguration {

    private final PortfolioSaga portfolioSaga;
    private final BootstrapConfigurationProperties bootstrapConfigurationProperties;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return this::run;
    }

    private void run(String... args) {
        log.debug("Wealth bundles: {}", bootstrapConfigurationProperties.getWealthBundles());
        List<WealthBundle> wealthBundles = bootstrapConfigurationProperties.getWealthBundles();
        if (wealthBundles == null) {
            log.error("Failed to load Wealth Bundle Structure");
            System.exit(1);
        } else {
            log.info("Bootstrapping Root Wealth Bundles Structure");

            Flux.fromIterable(bootstrapConfigurationProperties.getWealthBundles())
                .map(PortfolioTask::new)
                .flatMap(portfolioSaga::executeTask)
                .collectList()
                .block();
            log.info("Finished bootstrapping Approvals Structure");
            System.exit(0);
        }
    }
}
