package com.ra.model.dao.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    @NotEmpty(message = "Vui long nhap user vao")
    private String userName;
    private String fullName;
    @Size(min = 3,message = "Nhap mat khau it nhat 6 ky tu")
    private String password;
}
