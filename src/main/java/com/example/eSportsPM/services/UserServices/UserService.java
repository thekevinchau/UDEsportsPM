package com.example.eSportsPM.services.UserServices;

import com.example.eSportsPM.DTOs.UserCreation;
import com.example.eSportsPM.DTOs.UserDTO;
import com.example.eSportsPM.models.User;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.security.JwtUtil;
import com.example.eSportsPM.services.UserProfileService.CreateProfileService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

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

    public ResponseEntity<String> register(UserCreation userInfo){
        Optional<User> userOptional = userRepository.findByUsername(userInfo.getUsername());
        if (userOptional.isEmpty()){
            String currentPassword = userInfo.getPassword();
            User newUser = new User();

            //Set metadata
            newUser.setUsername(userInfo.getUsername().toLowerCase());
            newUser.setPassword(passwordEncoder.encode(currentPassword));
            newUser.setEmail(userInfo.getEmail().toLowerCase());
            newUser.setRole("ROLE_USER");

            //Set verification details
            newUser.setVerified(false);
            newUser.setVerificationToken(UUID.randomUUID());
            newUser.setVerificationExpiration(OffsetDateTime.now().plusMinutes(15)); //have Amazon SES send this verification token now to the email
            User savedUser = userRepository.save(newUser);

            //TODO: MOVE THIS LOGIC INTO THE GET REQUEST FOR THE VERIFICATION LINK
            createProfileService.createPrivateProfile(savedUser, userInfo.getFullName());
            createProfileService.createPublicProfile(savedUser, "");
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
    }


    public ResponseEntity<String> login(User user) throws AccessDeniedException {
        System.out.println(user.getVerified());
        /*
        if (!user.getVerified()){
            throw new AccessDeniedException("Your account is not currently verified!");
        }
        
         */
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
        }
        return ResponseEntity.ok(new UserDTO(userOptional.get()));
    }
}

