package com.example.eSportsPM.services.UserServices;

import com.example.eSportsPM.DTOs.UserDTOs.UserCreationDTO;
import com.example.eSportsPM.DTOs.UserDTOs.UserDTO;
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

    public ResponseEntity<String> register(UserCreationDTO userInfo){
        Optional<User> userOptional = userRepository.findByUsername(userInfo.getUsername());
        if (userOptional.isEmpty()){
            String currentPassword = userInfo.getPassword();
            User savedUser = new User();
            savedUser.setUsername(userInfo.getUsername());
            savedUser.setPassword(passwordEncoder.encode(currentPassword));
            savedUser.setEmail(userInfo.getEmail());
            savedUser.setRole("ROLE_USER");
            userRepository.save(savedUser);
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.ok("user already exists!");
    }


    public ResponseEntity<String> login(User user){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        );

        Authentication authentication = manager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtUtil.generateToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());
        return ResponseEntity.ok(jwtToken);
    }

    public ResponseEntity<UserDTO> getUser(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()){
            throw new RuntimeException("Not found");
        }
        return ResponseEntity.ok(new UserDTO(userOptional.get()));
    }
}

