package com.reactive.profile.service;

import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.reactive.profile.config.ServerIPProperties;
import com.reactive.profile.custom.exception.BusinessException;
import com.reactive.profile.dao.UserDao;
import com.reactive.profile.dto.User;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ServerIPProperties serverProperties;
	
	public Flux<User> getUserAll(){
		log.info(" UserService :: getUserAll :: Entry");
		try{
		 Flux<User> user =  userDao.getUser();
		 log.info(" IP ->>" + serverProperties.getIps() + " Name ->>" + serverProperties.getName() );
		 return user;
		} catch (Exception e){
			log.error("UserService :: getUserAll:: Error" + e.getMessage());
		}
		return Flux.just(new User());
	}
	
	public Mono<User> getUserId(String input){
		log.info(" UserService :: getUserId :: Entry");
		try{

		return userDao.getUser()
		 .filter(p -> p.getId() == Integer.valueOf(input))
		 //.doOnError((Consumer<? super Throwable>) new BusinessException(input))
		 .switchIfEmpty( Mono.error(new BusinessException(HttpStatus.BAD_REQUEST.toString(), "Empty message")))
		 .next();
		}catch(Exception e){
			log.error(" Error :: Profie get by UersId");
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Empty message");
		}
	}
	
	/*public Mono<User> getUserId(String input){
		log.info(" UserService :: getUserId :: Entry");
		try{

		return userDao.getUser()
		 .filter(p -> p.getId() == Integer.valueOf(input))
		 //.doOnError((Consumer<? super Throwable>) new BusinessException(input))
		 .switchIfEmpty(Mono.error(new BusinessException(HttpStatus.BAD_REQUEST, "Empty message")))
		 .next();
		}catch(Exception e){
			log.info(" Eororo");
			throw new BusinessException(HttpStatus.BAD_REQUEST, "Empty message");
		}
	}*/
	
	public Mono<User> createUserId(String input){
		log.info(" UserService :: doUserId :: Entry");
		Random random = new Random();   
		return Mono.just(new User(random.nextInt(50), input , "9878765653"));
	}

}
