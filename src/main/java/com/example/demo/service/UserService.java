package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.dto.UserResponseDTO;
import com.example.demo.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // declare as Spring Bean to be able to use it in UserController constructor.Business logic here
@Slf4j // Lombok annotation for logging
public class UserService {

    //private static final Logger log = LoggerFactory.getLogger(UserService.class); no need to declare log when there is @Slf4j
    private final UserRepository userRepository;
    private final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);//https://mapstruct.org/

    public UserService(UserRepository userRepository) {//UserRepository Dep Inj
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> findAll(){
        return INSTANCE.mapUserToUserResponseDTO(userRepository.findAll());
        //return userRepository.findAll();
    }

    public UserResponseDTO create(User user) {
        log.info("Trying to create User: {}", user);
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            throw new IllegalStateException("User with email: "+user.getEmail()+" already exists");
        }
        //orders
//        if (user.getOrders() != null) {
//            for (Order order : user.getOrders()) {
//                order.setUser(user);
//            }
//        }
        return INSTANCE.mapUserToUserResponseDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        log.info("Trying to delete User with id: {}", id);
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new ResourceNotFoundException("No user found for the given id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, String name, String email) {
        log.info("Trying to update User with id: {}", id);
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new ResourceNotFoundException("No user found for the given id: " + id);
        }
        User user = optionalUser.get();
        //check email
        if(email != null && !email.equals(user.getEmail())){
            Optional<User> foundByEmail = userRepository.findByEmail(email);
            if(foundByEmail.isPresent()){
                throw new ResourceNotFoundException("User with email: "+email+" already exists");
            }
            user.setEmail(email);
        }
        //check name
        if(name != null && !name.equals(user.getName())){
            user.setName(name);
        }

        //userRepository.save(user); no need to save when @Transactional
        return INSTANCE.mapUserToUserResponseDTO(user);
    }
}