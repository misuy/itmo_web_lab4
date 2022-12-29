package com.example.lab4.controllers;

import com.example.lab4.entities.UserDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public void newUser(@Autowired JdbcUserDetailsManager users, @RequestBody UserDto user, HttpServletResponse response) {
        users.setCreateUserSql(JdbcUserDetailsManager.DEF_CREATE_USER_SQL);
        users.setUserExistsSql(JdbcUserDetailsManager.DEF_USER_EXISTS_SQL);
        if (users.userExists(user.getUsername())) response.setStatus(400);
        else {
            users.createUser(User.builder().username(user.getUsername()).password(user.getPassword()).roles("USER").build());
            response.setStatus(200);
        }
    }
}
