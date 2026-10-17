package com.example.api_gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingOrdersFilter extends AbstractGatewayFilterFactory<LoggingOrdersFilter.Config> {
    public LoggingOrdersFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
         return (exchange, chain) -> {
            System.out.println("Order request received: "
                    + exchange.getRequest().getURI());

            return chain.filter(exchange);
        };
    }

    public static class Config{

    }
}
