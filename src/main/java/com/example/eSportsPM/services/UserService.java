package com.example.eSportsPM.services;

import com.example.eSportsPM.models.User;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;

    @Autowired
    private JwtUtil jwtUtil;;

    private final UserRepository userRepository;


    public UserService(PasswordEncoder passwordEncoder, AuthenticationManager manager, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.manager = manager;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> register(User user){
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isEmpty()){
            String currentPassword = user.getPassword();
            user.setPassword(passwordEncoder.encode(currentPassword));
            userRepository.save(user);
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.ok("user already exists!");
    }


    public ResponseEntity<String> login(User user){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        );

        Authentication authentication = manager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtUtil.generateToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());
        return ResponseEntity.ok(jwtToken);
    }
}

