package com.example.demo.service;

import com.example.demo.repository.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // declare as Spring Bean to be able to use it in UserController constructor.Business logic here
@Slf4j // Lombok annotation for logging
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {//UserRepository Dep Inj
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        /*1. hardcoded list of Users
        return List.of(
                new User(1L, "Sergey", "serg@mail.ru", LocalDate.of(1990, 1, 1), 35),
                new User(2L, "Mary", "mary@mail.ru", LocalDate.of(1991, 2, 2), 34),
                new User(3L, "Ivan", "ivan@mail.ru", LocalDate.of(1992, 3, 3), 33)
        );*/
        /*2. list of Users comes from DB*/
        return userRepository.findAll();
    }

    public User create(User user) {
        log.info("Trying to create User: {}", user);
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            throw new IllegalStateException("User with email: "+user.getEmail()+" already exists");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        log.info("Trying to delete User with id: {}", id);
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new IllegalStateException("No user found for the given id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public User updateUser(Long id, String name, String email) {
        log.info("Trying to update User with id: {}", id);
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new IllegalStateException("No user found for the given id: " + id);
        }
        User user = optionalUser.get();
        //check email
        if(email != null && !email.equals(user.getEmail())){
            Optional<User> foundByEmail = userRepository.findByEmail(email);
            if(foundByEmail.isPresent()){
                throw new IllegalStateException("User with email: "+email+" already exists");
            }
            user.setEmail(email);
        }
        //check name
        if(name != null && !name.equals(user.getName())){
            user.setName(name);
        }

        //userRepository.save(user); no need to save when @Transactional
        return user;
    }
}
