package com.bansi.collectionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
public class CollectionServiceApplication {
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplage(){
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(CollectionServiceApplication.class, args);
	}

}
