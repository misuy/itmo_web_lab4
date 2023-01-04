package com.example.lab4.controllers;

import com.example.lab4.entities.UserDto;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Controller
public class AuthenticationController {

    @Autowired
    private JdbcUserDetailsManager users;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public @ResponseBody void newUser(@RequestBody UserDto user, @Autowired BCryptPasswordEncoder bCryptPasswordEncoder) throws IllegalArgumentException {
        if (users.userExists(user.getUsername())) throw new IllegalArgumentException("Username already exists");
        users.createUser(User.builder().username(user.getUsername()).password(bCryptPasswordEncoder.encode(user.getPassword())).roles("USER").build());
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

}
