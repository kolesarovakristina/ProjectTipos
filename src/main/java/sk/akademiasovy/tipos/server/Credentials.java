package sk.akademiasovy.tipos.server;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Credentials {
    @JsonProperty("login")
    public String login;

    @JsonProperty("password")
    public String password;

    @JsonProperty("token")
    public String token;


    public String getUsername()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }
}