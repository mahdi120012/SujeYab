package ir.e.sujeyab.models;

import com.google.gson.annotations.SerializedName;

public class RegisterModel {
    private String username;
    private String password;


    public RegisterModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}