package com.example.VSAPIBot.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// то что пользователь аутентифицирован в системе
public class JwtAuthentication implements Authentication {


    private String authority;

    private final String token;
    private UserDetails userDetails;
    private boolean isAuthenticated;

    public JwtAuthentication(String token){
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
         this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return token;
    }


    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getAuthority() {
        return authority;
    }

    public String getToken() {
        return token;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}

