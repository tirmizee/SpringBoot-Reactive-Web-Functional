package com.tirmizee.router.member.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponse {

    private String memberId;
    private String memberName;
    private boolean isActive;

}
