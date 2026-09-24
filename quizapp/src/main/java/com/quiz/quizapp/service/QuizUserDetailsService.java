package com.quiz.quizapp.service;

import com.quiz.quizapp.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuizUserDetailsService implements UserDetailsService {

    private final Map<String, User> users = new HashMap<>();
    private final PasswordEncoder passwordEncoder;

    public QuizUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        // seed one admin and one regular user for testing
        users.put("admin", new User("admin", passwordEncoder.encode("admin123"), "ROLE_ADMIN"));
        users.put("user", new User("user", passwordEncoder.encode("user123"), "ROLE_USER"));
    }

    public void registerUser(String username, String password, String role) throws Exception {
        if (users.containsKey(username)) {
            throw new Exception("User already exists: " + username);
        }
        String formattedRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();
        User newUser = new User(username, passwordEncoder.encode(password), formattedRole);
        users.put(username, newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority(user.getRole()))
                .build();
    }
}