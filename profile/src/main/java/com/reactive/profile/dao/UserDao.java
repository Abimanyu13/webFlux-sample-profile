package com.reactive.profile.dao;

import java.time.Duration;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Component;

import com.reactive.profile.dto.User;

import reactor.core.publisher.Flux;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class UserDao {

	/*
	 * private static void sleepExecution(int i){ try { Thread.sleep(1000); }
	 * catch (InterruptedException e) { e.printStackTrace(); } }
	 * 
	 * public List<User> getUser(){ return IntStream.rangeClosed(1, 10)
	 * .peek(UserDao::sleepExecution) .peek(i ->
	 * System.out.println("processing count : " + i)) .mapToObj(i -> new User(i,
	 * "customer" + i)) .collect(Collectors.toList()); }
	 */

	public Flux<User> getUser() {
		//User u = new User();
		return Flux.range(1, 6)
				//.delayElements(Duration.ofSeconds(1))
				.doOnNext(
						i -> System.out.println("count in stream flow : " + i))
				.map(i -> new User(i, "User-" + i, "982712311"+i++));
	}

}