package com.tirmizee.router.member.data;

import lombok.Data;

@Data
public class MemberRequest {
    private String memberId;
    private String memberName;
    private boolean isActive;
}
