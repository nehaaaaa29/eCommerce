package com.example.api_gateway.filters;

import com.example.api_gateway.service.JwtService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Component
@Slf4j
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    private final JwtService jwtService;

    public AuthenticationGatewayFilterFactory(JwtService jwtService) {
        super(Config.class);
        this.jwtService=jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->{

           String authorizationHeaders=exchange.getRequest().getHeaders().getFirst("Authorization");
            if (authorizationHeaders == null ||
                    !authorizationHeaders.startsWith("Bearer ")) {
                exchange.getResponse()
                        .setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authorizationHeaders.substring(7).trim();
            log.info("Token length: {}", token.length());
            log.info("Token has whitespace: {}",
                    token.chars().anyMatch(Character::isWhitespace));

            Long userId =jwtService.getUserIdFromToken(token);
            ServerHttpRequest request = exchange.getRequest()
                    .mutate()
                    .header("X-User-Id",userId.toString())
                    .build();
            return chain.filter(exchange.mutate()
                    .request(request)
                    .build());

        } ;
    }

    @Data
    public static class Config{
    private boolean isEnabled;
}

}
