package com.example.VSAPIBot.SignIn;

import com.example.VSAPIBot.DTO.AuthDto;
import com.example.VSAPIBot.DTO.TokenDto;

public interface SignInService {

    TokenDto signIn(AuthDto auth);
}
