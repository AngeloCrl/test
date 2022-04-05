package com.assignment.app.rest.user.serializer;

import com.assignment.app.rest.user.dto.UserRoleDto;
import com.assignment.app.rest.user.model.RoleType;
import com.assignment.app.rest.user.model.UserRole;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class UserRoleDeserializer extends StdDeserializer<UserRoleDto> {

    public UserRoleDeserializer() {
        this(null);
    }

    public UserRoleDeserializer(Class<UserRole> t) {
        super(t);
    }

    @Override
    public UserRoleDto deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.toString().replace("\"", "");
        return new UserRoleDto(RoleType.valueOf(type));
    }
}
