package com.kozlovskaya.springdata.lesson7.controllers;



import com.kozlovskaya.springdata.lesson7.entities.User;
import com.kozlovskaya.springdata.lesson7.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;

    @GetMapping("/")
    public String homePage(){ return "home"; }

    @GetMapping("/unsecured")
    public String unsecuredPage(){
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authenticatedPage(){
        return "authenticated";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/user_info")
    public String daoTestPage(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
    }

    @PostMapping("/admin")
    @PreAuthorize("hasAuthority('PERMISSION_WRITE')")
    public User addProduct(@RequestBody User user){
        userService.save(user);
        return user;
    }



}
