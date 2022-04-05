package com.assignment.app.security;

import com.assignment.app.rest.user.model.User;
import com.assignment.app.rest.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + email + "' not found");
        }

        boolean isDisabled = !user.isEmailVerified();

        return org.springframework.security.core.userdetails.User.withUsername(email).password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false).accountLocked(false)
                .credentialsExpired(false).disabled(isDisabled).build();
    }

}
