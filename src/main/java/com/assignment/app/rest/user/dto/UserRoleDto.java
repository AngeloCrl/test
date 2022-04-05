package com.assignment.app.rest.user.dto;

import com.assignment.app.rest.user.model.RoleType;
import com.assignment.app.rest.user.serializer.UserRoleDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = UserRoleDeserializer.class)
public class UserRoleDto {

    @JsonValue
    @JsonProperty("userRoles")
    private RoleType role;
}
