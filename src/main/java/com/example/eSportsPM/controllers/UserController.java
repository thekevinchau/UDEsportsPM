package controllers;

import models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.GetUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetUserService getUserService;

    public UserController(GetUserService getUserService) {
        this.getUserService = getUserService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
        return getUserService.getAllUsers();
    }
}
