package com.assignment.app.rest.user.dto;

import com.assignment.app.rest.user.constraints.ValidPassword;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email(message = "Email should be valid")
    @NotNull(message = "please specify an email address")
    private String email;

    @ValidPassword
    private String password;

    @JsonUnwrapped
    List<UserRoleDto> roles;

}
