package com.tirmizee.router.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userRoute(UserHandler handler) {
        return RouterFunctions
                .route(GET("/user/all"), handler::all)
                .andRoute(GET("/user/{id}"), handler::get)
                .andRoute(POST("/user/create"), handler::create)
                .andRoute(DELETE("/user/{id}"), handler::delete);
    }

}
