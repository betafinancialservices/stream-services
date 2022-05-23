package com.backbase.stream.compositions.legalentity.core.config;

import com.backbase.stream.compositions.legalentity.integration.client.LegalEntityIntegrationApi;
import com.backbase.stream.compositions.product.ApiClient;
import com.backbase.stream.compositions.product.client.ProductCompositionApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.DateFormat;

@Configuration
@AllArgsConstructor
@EnableWebFluxSecurity
@EnableConfigurationProperties(LegalEntityConfigurationProperties.class)
public class LegalEntityConfiguration {
    private final LegalEntityConfigurationProperties legalEntityConfigurationProperties;

    @Bean
    @Primary
    public LegalEntityIntegrationApi legalEntityIntegrationApi() {
        LegalEntityIntegrationApi legalEntityIntegrationApi = new LegalEntityIntegrationApi();
        legalEntityIntegrationApi.getApiClient().setBasePath(legalEntityConfigurationProperties.getIntegrationBaseUrl());
        return legalEntityIntegrationApi;
    }

    @Bean
    @Primary
    public ProductCompositionApi productCompositionApi(ApiClient productClient) {
        return new ProductCompositionApi(productClient);
    }

    @Bean
    public ApiClient productClient(
            WebClient dbsWebClient,
            ObjectMapper objectMapper,
            DateFormat dateFormat) {
        ApiClient apiClient = new ApiClient(dbsWebClient, objectMapper, dateFormat);
        apiClient.setBasePath(legalEntityConfigurationProperties.getChains().getProductComposition().getBaseUrl());

        return apiClient;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable().build();
    }
}
