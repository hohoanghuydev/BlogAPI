package com.example.blog_api.service;

import com.example.blog_api.entity.User;
import com.example.blog_api.entity.UserPrincipal;
import com.example.blog_api.exception.ResourceNotFound;
import com.example.blog_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;

        if (userRepo.count() == 0) {
            String passwordHash = "$2a$12$nmKyOkkYvXH.CFz7PORQIOoT7WYN90vr697MMxyw.u5hVoWs1OKmG";
            user = new User(username, passwordHash);
        } else {
            user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        }

        return new UserPrincipal(user);
    }
}
