package ru.herewego.bootJS.controllerREST;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> list() {
        return userService.listUsers();
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteById(id);
        return String.format("User with id = %d was deleted", id);
    }

    @PostMapping("/admin")
    public User create(@RequestBody User user) {
        userService.add(user);
        return user;
    }

    @PutMapping("/admin")
    public User edit(@RequestBody User user) {
        userService.update(user.getId(), user);
        return user;
    }
}
