package com.ra.model.dao.response;

import com.ra.model.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String userName;
    private String fullName;
    private Boolean status;

    public UserResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.fullName = user.getFullName();
        this.status = user.getStatus();
    }
}
