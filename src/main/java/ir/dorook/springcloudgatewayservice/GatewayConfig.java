package ir.dorook.springcloudgatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableHystrix
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Add a simple re-route from: /get to: http://httpbin.org:80
                // Add a simple "Hello:World" HTTP Header

                .route(p -> p
                        .path("/all")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-key", "1afbc0d72emshfcca22b452ad419p127295jsn3d75cacc9fed")
                                .addRequestHeader("x-rapidapi-key", "1afbc0d72emshfcca22b452ad419p127295jsn3d75cacc9fed")
                                .hystrix(config -> config.setName("countries-service")
                                        .setFallbackUri("forward:/countriesfallback"))
                        )

                        .uri("https://restcountries-v1.p.rapidapi.com"))

                .route(p -> p
                        .path("/v1/joke")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-key", "1afbc0d72emshfcca22b452ad419p127295jsn3d75cacc9fed")
                                .addRequestHeader("x-rapidapi-host", "joke3.p.rapidapi.com")
                                .addRequestHeader("useQueryString", String.valueOf(true))
                                .hystrix(config -> config.setName("joke1-service")
                                        .setFallbackUri("forward:/jok1fallback"))
                        )
                        .uri("https://joke3.p.rapidapi.com"))

                .route(p -> p
                        .path("/joke/Any")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-key", "1afbc0d72emshfcca22b452ad419p127295jsn3d75cacc9fed")
                                .addRequestHeader("x-rapidapi-host", "jokeapi-v2.p.rapidapi.com")
                                .addRequestHeader("useQueryString", String.valueOf(true))
                                .hystrix(config -> config.setName("joke2-service")
                                        .setFallbackUri("forward:/jok2fallback"))
                        )
                        .uri("https://jokeapi-v2.p.rapidapi.com"))


                .route(p -> p
                        .path("/get") // intercept calls to the /get path
                        .filters(f -> f.addRequestHeader("Hello", "World")
                                .hystrix(config -> config.setName("get-service"))
                        ) // add header
                        .uri("http://httpbin.org:80"))
                .build();
    }

}
