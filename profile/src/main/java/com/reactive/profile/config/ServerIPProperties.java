package com.reactive.profile.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "microservices")
@Data
public class ServerIPProperties  {
	private String servers;
	private String name;
    private String ips;

}
