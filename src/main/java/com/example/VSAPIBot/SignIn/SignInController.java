package com.example.VSAPIBot.SignIn;

import com.example.VSAPIBot.DTO.AuthDto;
import com.example.VSAPIBot.DTO.TokenDto;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PreAuthorize("permitAll()")
    @PostMapping("/signIn")
    @ResponseBody
    public TokenDto signIn(@RequestBody AuthDto auth){
        System.out.println("dfdf");
      return signInService.signIn(auth);
    }
}
