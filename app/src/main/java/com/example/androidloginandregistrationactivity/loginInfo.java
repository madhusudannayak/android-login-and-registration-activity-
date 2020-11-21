package com.example.androidloginandregistrationactivity;

public class loginInfo {

    private String name;
    private String password;

    loginInfo(String username, String Password){
        this.name=username;
        this.password=Password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
