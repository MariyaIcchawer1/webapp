package com.example.Webappex.payload;



public class JwtAuthresponse {
    private String token;

    public JwtAuthresponse() {
    }

    public JwtAuthresponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JwtAuthresponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
