package com.cruedadam.geteway.filters;

import com.cruedadam.geteway.dtos.TokenDTO;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GatewayFilter {

    private final WebClient webClient;

    private static final String AUTH_VALIDATE_URI = "http://ms-auth-service:3030/auth-server/auth/jwt";
    private static final String ACCESS_TOKEN_HEADER_NAME = "accessToken";

    public AuthFilter(){
        this.webClient = WebClient.builder().build();
    }



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
            return this.onError(exchange);
        }

        final var tokenHeader = exchange
                .getRequest()
                .getHeaders()
                .get(HttpHeaders.AUTHORIZATION).get(0);

        final var chunks = tokenHeader.split(" ");

        if (chunks.length != 2 || !chunks[0].equals("Bearer")){
            return this.onError(exchange);
        }

        final var token = chunks[1];
        System.out.println("El token es: "+token);
        return this.webClient
                .post()
                .uri(AUTH_VALIDATE_URI)
                .header("accessToken", token)
                .retrieve().bodyToMono(TokenDTO.class)
                .map(response -> exchange)
                .flatMap(chain::filter);
    }

    private Mono<Void> onError (ServerWebExchange exchange){
        final var response = exchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        return response.setComplete();
    }
}
