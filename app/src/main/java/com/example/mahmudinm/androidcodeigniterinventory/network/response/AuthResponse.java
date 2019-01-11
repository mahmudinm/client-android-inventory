package com.example.mahmudinm.androidcodeigniterinventory.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponse extends BaseResponse{

    @Expose
    @SerializedName("token") String token;

    @Expose
    @SerializedName("username") String username;

    @Expose
    @SerializedName("id") String id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
