package com.tirmizee.router.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class MemberRouter {

    @Bean
    public RouterFunction<ServerResponse> memberRoute(MemberHandler handler) {
        return RouterFunctions
                .route(GET("/member/all"), handler::all)
                .andRoute(GET("/member/{id}"), handler::get)
                .andRoute(POST("/member/create"), handler::create)
                .andRoute(DELETE("/member/{id}"), handler::delete);
    }

}
