package rs.njmarko.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder)
	{
		return routeLocatorBuilder.routes()
				.route("UserService", rt -> rt.path("/api/user/**")
						.filters(f -> f.rewritePath("/api/user/(?<segment>.*)", "/${segment}"))
						.uri("lb://UserService"))

				.route("SciPaperService", rt -> rt.path("/api/scipaper/**")
						.filters(f -> f.rewritePath("/api/scipaper/(?<segment>.*)", "/${segment}"))
						.uri("lb://SciPaperService"))

				.route("LibraryService", rt -> rt.path("/api/library/**")
						.filters(f -> f.rewritePath("/api/library/(?<segment>.*)", "/${segment}"))
						.uri("lb://LibraryService"))

				.build();
	}

}
