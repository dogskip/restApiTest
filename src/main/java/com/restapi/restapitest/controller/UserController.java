package com.restapi.restapitest.controller;

import com.restapi.restapitest.dto.UserDto;
import org.springframework.web.bind.annotation.*;
import com.restapi.restapitest.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 모든 유저 조회
    @GetMapping
    public List<UserDto> getAllUsers() {
        System.out.println(userService.getAllUsers());
        return userService.getAllUsers();
    }

    // 유저 생성
    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}
