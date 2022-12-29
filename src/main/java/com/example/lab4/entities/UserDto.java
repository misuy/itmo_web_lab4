package com.example.lab4.entities;


public class UserDto {
    private String username;
    private String password;

    public UserDto() {}

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }
}
