package com.example.photoBlog.user;

public class UserDTO {
    private String username;
    private String password;


    public UserDTO() {}

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void User(String username, String password) {
        // this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
       this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' +
                ", password='" + password + '\'' + '}';
    }

}