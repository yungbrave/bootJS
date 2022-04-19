package ru.herewego.bootJS.controllerREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.herewego.bootJS.model.User;
import ru.herewego.bootJS.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/api/users/")
public class UserControllerREST {

    @Autowired
    public UserControllerREST(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<User> user() {
        return new ResponseEntity<>(userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()), HttpStatus.OK);
    }
}