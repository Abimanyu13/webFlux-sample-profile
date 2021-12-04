package com.reactive.profile.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@ControllerAdvice
@Slf4j
public class ExceptionController {
	
	@ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<Object>> myMessage(BusinessException c){
		log.info("ExceptionController :: "+ c);
      return Mono.just(new ResponseEntity<>(c.getMessage(), HttpStatus.valueOf(c.getErrorCode())));
    }
	
}
