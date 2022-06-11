package com.tirmizee.router.user.data;

import lombok.Data;

@Data
public class UserRequest {
    private String userId;
    private String username;
    private boolean isActive;
}
