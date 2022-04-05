package com.assignment.app.rest.user.controller;

import com.assignment.app.rest.user.dto.JwtDto;
import com.assignment.app.rest.user.dto.RegisterDto;
import com.assignment.app.rest.user.dto.UserMapper;
import com.assignment.app.rest.user.service.UserService;
import com.assignment.app.rest.utils.dto.GenericResponse;
import com.assignment.app.rest.user.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<GenericResponse> register(@Valid @RequestBody RegisterDto registerDto) {
        System.out.println("user1");
        userService.register(userMapper.registerDtoToUser(registerDto), true);
        System.out.println("user2");
        return new ResponseEntity<>(new GenericResponse("Registered Successfully"), HttpStatus.OK);
    }

    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtDto> login(@Valid @RequestBody SignInDto signInDto) {
        String jwt = userService.signIn(signInDto);
        return new ResponseEntity<>(new JwtDto(jwt), HttpStatus.OK);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<JwtDto> refresh(HttpServletRequest req) {
        String jwt = userService.refresh(req.getRemoteUser());
        return new ResponseEntity<>(new JwtDto(jwt), HttpStatus.OK);
    }
}
