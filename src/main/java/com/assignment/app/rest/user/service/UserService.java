package com.assignment.app.rest.user.service;

import com.assignment.app.rest.user.dto.SignInDto;
import com.assignment.app.rest.user.model.*;
import com.assignment.app.rest.user.repository.UserRepository;
import com.assignment.app.rest.utils.exception.CustomException;
import com.assignment.app.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public User register(User user, boolean shouldCreateToken) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CustomException("Email is already in use", HttpStatus.BAD_REQUEST);
        }
        user = updatePasswordAndSave(user, user.getPassword());
        if (shouldCreateToken) {
            tokenService.createUserToken(user, UserTokenType.VERIFY_EMAIL);
        }
        return user;
    }

    public String signIn(SignInDto signInDto) {
        try {
            User user = userRepository.findByEmail(signInDto.getEmail());
            if (user == null || user.getRoles().stream().noneMatch(userRole -> userRole.getRole().equals(RoleType.ROLE_CLIENT))) {
                throw new CustomException("Invalid email/password combination is wrong or you didn't verify your email", HttpStatus.BAD_REQUEST);
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));
            return jwtTokenProvider.createToken(signInDto.getEmail(), user.getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid email/password combination is wrong or you didn't verify your email", HttpStatus.BAD_REQUEST);
        }
    }

    public String refresh(String email) {
        return jwtTokenProvider.createToken(email, userRepository.findByEmail(email).getRoles());
    }

    @Transactional
    public User updatePasswordAndSave(User user, String newPassword) {
        String pass = passwordEncoder.encode(newPassword);
        user.setPassword(pass);
        return userRepository.save(user);
    }
}
