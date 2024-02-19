package com.agn.login.service;

import com.agn.login.controller.dto.UserDto;
import com.agn.login.entity.User;
import com.agn.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getUsers() {
        List<UserDto> userDtoList = userRepository.findAll()
                .stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .role(user.getRole())
                        .materias(user.getMaterias())
                        .build()).toList();
        return ResponseEntity.ok(userDtoList);
    }

    public ResponseEntity<?> getUser(Long id) {
        Optional<User> res = userRepository.findUserById(id);
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        User user = res.get();
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .materias(user.getMaterias())
                .build();
        return ResponseEntity.ok(userDto);

    }

    public ResponseEntity<?> createUser(UserDto userDto) throws URISyntaxException {
        Optional<User> res = userRepository.findUserByUsername(userDto.getUsername());
        if (res.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        userRepository.save(User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .materias(userDto.getMaterias())
                .build());
        return ResponseEntity.created(new URI("/user")).build();
    }

    public ResponseEntity<?> updateUser(UserDto userDto) {
        Optional<User> res = userRepository.findUserById(userDto.getId());
        if (res.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User user = res.get();
        userRepository.save(User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .materias(user.getMaterias())
                .build());
        return ResponseEntity.ok(user);
    }

}
