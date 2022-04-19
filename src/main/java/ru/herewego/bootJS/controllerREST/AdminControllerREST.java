package ru.herewego.bootJS.controllerREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.herewego.bootJS.model.User;
import ru.herewego.bootJS.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/api/users/")
public class AdminControllerREST {

    @Autowired
    public AdminControllerREST(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("/admin")
    public ResponseEntity<List<User>> list() {
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        userService.deleteById(id);
        return new ResponseEntity<>(String.format("User with id = %d was deleted", id), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> create(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/admin")
    public ResponseEntity<User> edit(@RequestBody User user) {
        if (!(user.getPassword().length() == 0)) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        userService.update(user.getId(), user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
