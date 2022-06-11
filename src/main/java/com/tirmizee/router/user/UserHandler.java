package com.tirmizee.router.user;

import com.tirmizee.router.user.data.UserRequest;
import com.tirmizee.router.user.data.UserResponse;
import com.tirmizee.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> all(ServerRequest request) {
        return ServerResponse.ok()
                .body(userService.all(), UserResponse.class)
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> get(ServerRequest request) {
        var id = request.pathVariable("id");
        return  ServerResponse.ok()
                .body(userService.getById(id), UserResponse.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(UserRequest.class)
                .flatMap(requestUser -> userService.insert(requestUser))
                .flatMap(responseUser -> ServerResponse.ok().body(Mono.just(responseUser), UserResponse.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        var id = request.pathVariable("id");
        return ServerResponse
                .noContent()
                .build(userService.delete(id));
    }

}
