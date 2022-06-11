package com.tirmizee.service;

import com.tirmizee.router.member.data.MemberRequest;
import com.tirmizee.router.member.data.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class MemberService {

    public Mono<MemberResponse> getById(String id) {
        return Mono.just(new MemberResponse(id, "name", true));
    }

    public Flux<MemberResponse> all() {
        List<MemberResponse> list = Arrays.asList(
            new MemberResponse("1", "name1", true),
            new MemberResponse("2", "name2", true),
            new MemberResponse("3", "name3", true)
        );
        return Flux.fromIterable(list);
    }

    public Mono<MemberResponse> insert(MemberRequest req) {
        return Mono.just(new MemberResponse(req.getMemberId(), req.getMemberName(), req.isActive()));
    }

    public Mono<Void> delete(String id) {
        return Mono.empty();
    }

}
