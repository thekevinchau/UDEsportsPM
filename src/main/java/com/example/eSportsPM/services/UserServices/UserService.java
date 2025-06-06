package com.example.eSportsPM.services.UserServices;

import com.example.eSportsPM.DTOs.Organization.OrgProfileDTO;
import com.example.eSportsPM.DTOs.UserCreation;
import com.example.eSportsPM.DTOs.UserDTO;
import com.example.eSportsPM.exceptions.NotFoundException;
import com.example.eSportsPM.models.OrgProfile;
import com.example.eSportsPM.models.User;
import com.example.eSportsPM.repositories.OrgProfileRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.security.JwtUtil;
import com.example.eSportsPM.utils.Utils;
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
    private final OrgProfileRepository profileRepository;

    @Autowired
    private JwtUtil jwtUtil;;

    private final UserRepository userRepository;


    public UserService(PasswordEncoder passwordEncoder, AuthenticationManager manager, OrgProfileRepository profileRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.manager = manager;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
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


    public ResponseEntity<String> setPrimaryProfile (UUID profileId){
        User currentUser = Utils.getUser(userRepository);
        Optional<OrgProfile> queriedProfile = profileRepository.findById(profileId);
        if (queriedProfile.isEmpty()){
            throw new NotFoundException("Profile not found!");
        }
        currentUser.setCurrentOrgProfile(queriedProfile.get());
        userRepository.save(currentUser);
        return ResponseEntity.ok("Successfully saved primary profile!");
    }
}

