package com.agn.login.service;

import com.agn.login.entity.User;
import com.agn.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<Object> getUser(Long id) {
        Optional<User> res = userRepository.findUserById(id);
        if (res.isPresent()) {
            Optional<User> user = userRepository.findById(id);
            return new ResponseEntity<>(
                    user,
                    HttpStatus.OK
            );

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> createUser(User user) {
        Optional<User> res = userRepository.findUserByEmail(user.getEmail());
        if (res.isEmpty()) {
            userRepository.save(user);
            return new ResponseEntity<>(
                    user,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    public ResponseEntity<Object> updateUser(User user) {
        Optional<User> res = userRepository.findUserById(user.getId());
        if (res.isPresent()) {
            userRepository.save(user);
            return new ResponseEntity<>(
                    user,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
