package com.tirmizee.router.user.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private String userId;
    private String username;
    private boolean isActive;
}
