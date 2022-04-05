package com.assignment.app.rest.user.dto;

import com.assignment.app.rest.user.constraints.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {

    @ValidPassword
    private String password;

    private String email;
}
