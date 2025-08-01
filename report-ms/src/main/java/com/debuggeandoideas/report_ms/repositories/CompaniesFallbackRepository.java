package com.debuggeandoideas.report_ms.repositories;

import com.debuggeandoideas.report_ms.models.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
@Slf4j
public class CompaniesFallbackRepository {

    private final WebClient webClient;
    private final String uri;

    public CompaniesFallbackRepository(WebClient webClient, @Value("${fallback.uri}") String uri) {
        this.webClient = webClient;
        this.uri = uri;
    }

    public Company getByName(String name){
        log.warn("Calling companies fallback {}", uri);

        return this.webClient
                .get().uri(uri, name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Company.class)
                .log()
                .block();
    }
}
