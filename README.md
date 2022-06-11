# SpringBoot-Reactive-Web-Functional

### Router

```java

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

```

### Handler

```java

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

```

### Service (Mock)

```java

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

```