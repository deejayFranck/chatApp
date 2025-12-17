package com.project.chatapp.controller;

import com.project.chatapp.entity.User;
import com.project.chatapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final UserService userService;

    @GetMapping("/online-users")
    public List<User> getOnlineUsers(){
        return userService.getOnlineUsers();
    }


}
