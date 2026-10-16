package com.example.api_gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

public class LoggingOrdersFilter extends AbstractGatewayFilterFactory<LoggingOrdersFilter.Config> {
    public LoggingOrdersFilter(Class<Config> configClass) {
        super(configClass);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return null;
    }

    public static class Config{

    }
}
