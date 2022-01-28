package com.bannsi.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Autowired
	RouteDefinitionLocator locator;

	// @Bean
	// public List<GroupedOpenApi> apis() {
	// 	List<GroupedOpenApi> groups = new ArrayList<>();
	// 	List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
	// 	definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*service")).forEach(routeDefinition -> {
	// 		String name = routeDefinition.getId().replaceAll("service", "s");
	// 		log.info(name);
	// 		groups.add(GroupedOpenApi.builder()
	// 			.pathsToMatch("/" + name + "/**")
	// 			.setGroup(name).build());
	// 	});
	// 	return groups;
	// }
}
