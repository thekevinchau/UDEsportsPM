package com.example.eSportsPM.services.UserServices;

import com.example.eSportsPM.DTOs.UserDTOs.UserCreationDTO;
import com.example.eSportsPM.DTOs.UserDTOs.UserDTO;
import com.example.eSportsPM.exceptions.UserNotFound;
import com.example.eSportsPM.models.User;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.security.JwtUtil;
import com.example.eSportsPM.services.UserProfileServices.CreateProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final CreateProfileService createProfileService;


    public UserService(PasswordEncoder passwordEncoder, AuthenticationManager manager, UserRepository userRepository, CreateProfileService createProfileService) {
        this.passwordEncoder = passwordEncoder;
        this.manager = manager;
        this.userRepository = userRepository;
        this.createProfileService = createProfileService;
    }

    public ResponseEntity<String> register(UserCreationDTO userInfo){
        Optional<User> userOptional = userRepository.findByUsername(userInfo.getUsername());
        if (userOptional.isEmpty()){
            String currentPassword = userInfo.getPassword();
            User newUser = new User();
            newUser.setUsername(userInfo.getUsername());
            newUser.setPassword(passwordEncoder.encode(currentPassword));
            newUser.setEmail(userInfo.getEmail());
            newUser.setRole("ROLE_USER");
            User savedUser = userRepository.save(newUser);

            createProfileService.createProfile(savedUser.getId(), userInfo.getFullName()); //Will throw an exception if error happens here

            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
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
            throw new UserNotFound("User does not exist!");
        }
        return ResponseEntity.ok(new UserDTO(userOptional.get()));
    }
}

