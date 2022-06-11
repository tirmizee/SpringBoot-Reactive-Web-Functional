package com.tirmizee.service;

import com.tirmizee.router.member.data.MemberRequest;
import com.tirmizee.router.member.data.MemberResponse;
import com.tirmizee.router.user.data.UserRequest;
import com.tirmizee.router.user.data.UserResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    public Mono<UserResponse> getById(String id) {
        return Mono.just(new UserResponse(id, "name", true));
    }

    public Flux<UserResponse> all() {
        List<UserResponse> list = Arrays.asList(
                new UserResponse("1", "name1", true),
                new UserResponse("2", "name2", true),
                new UserResponse("3", "name3", true)
        );
        return Flux.fromIterable(list);
    }

    public Mono<UserResponse> insert(UserRequest req) {
        return Mono.just(new UserResponse(req.getUserId(), req.getUsername(), req.isActive()));
    }

    public Mono<Void> delete(String id) {
        return Mono.empty();
    }

}
