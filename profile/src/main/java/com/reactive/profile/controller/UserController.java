package com.reactive.profile.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.profile.custom.exception.BusinessException;
import com.reactive.profile.dto.User;
import com.reactive.profile.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/profile")
@Tag(name = "Profile APIs", description = "Profile APIs for demo purpose")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping(value ="/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllCustomersStream() {
        return service.getUserAll();
    }
	
	@GetMapping(value ="/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<User> getAllCustomersStream(@PathVariable String id)throws Exception {
		try{
        return service.getUserId(id);
        } catch (Exception e){
        	throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
        }
    }
	
	@PostMapping(value="/create/{name}")
	public Mono<User> createProfile(@PathVariable String name){
		return service.createUserId(name);
	}
	
}
