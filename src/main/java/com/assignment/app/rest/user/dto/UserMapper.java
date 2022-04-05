package com.assignment.app.rest.user.dto;

import com.assignment.app.rest.user.model.User;
import com.assignment.app.rest.user.model.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User registerDtoToUser(RegisterDto registerDto) {
        List<UserRoleDto> roles = registerDto.getRoles();
        registerDto.setRoles(null);

        User user = modelMapper.map(registerDto, User.class);
        roles.forEach(roleDto -> user.addRole(modelMapper.map(roleDto, UserRole.class)));

        return user;
    }
}
