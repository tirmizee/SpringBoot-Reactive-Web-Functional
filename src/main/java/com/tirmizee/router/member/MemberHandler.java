package com.tirmizee.router.member;

import com.tirmizee.router.member.data.MemberRequest;
import com.tirmizee.router.member.data.MemberResponse;
import com.tirmizee.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class MemberHandler {

    private final MemberService memberService;

    public Mono<ServerResponse> all(ServerRequest request) {
        var type = request.queryParam("type");
        if(type.isEmpty()) {
            return ServerResponse.notFound().build();
        }
        return ServerResponse
                .ok()
                .body(memberService.all(), MemberResponse.class);
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        var id = request.pathVariable("id");
        return ServerResponse
                .ok()
                .body(memberService.getById(id), MemberResponse.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        var requestBody = request.bodyToMono(MemberRequest.class).block();
        return ServerResponse
                .ok()
                .body(memberService.insert(requestBody), MemberResponse.class);
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        var id = request.pathVariable("id");
        return ServerResponse
                .noContent()
                .build(memberService.delete(id));
    }

}
