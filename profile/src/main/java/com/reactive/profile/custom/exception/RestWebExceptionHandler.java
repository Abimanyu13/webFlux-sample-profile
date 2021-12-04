package com.reactive.profile.custom.exception;

import org.junit.jupiter.api.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import reactor.core.publisher.Mono;

@Component
class RestWebExceptionHandler implements WebExceptionHandler{

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof BusinessException) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);

            // marks the response as complete
            return exchange.getResponse().setComplete();
        }
        return Mono.error(ex);
    }


}