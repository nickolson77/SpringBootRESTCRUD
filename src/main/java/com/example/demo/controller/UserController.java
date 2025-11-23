package com.example.demo.controller;

import com.example.demo.repository.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.UserResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//map Objects to JSON, @Controller doesn't do this
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {//UserService dependency injection
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping // <-- click here to test POST, generate request in HTTP client(For Idea Ultimate)
    public UserResponseDTO createUser(@RequestBody User user){ //@RequestBody - mapping from json to User obj
        return userService.create(user);
    }
    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable(name = "id") Long id){// need (name = "id") in case when path = "{id}" is different
        userService.deleteUser(id);
    }
    @PutMapping(path = "{id}")
    public UserResponseDTO updateUser(@PathVariable Long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email) {
        return userService.updateUser(id, name, email);
    }
}
