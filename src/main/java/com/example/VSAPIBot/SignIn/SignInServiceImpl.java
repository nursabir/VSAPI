package com.example.VSAPIBot.SignIn;

import com.example.VSAPIBot.DTO.AuthDto;
import com.example.VSAPIBot.DTO.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SignInServiceImpl implements SignInService{

    @Autowired
    private AccountRepository accountsRepository;

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDto signIn(AuthDto auth) {
        Optional<Account> accountOptional = accountsRepository.getAccountByEmail(auth.getEmail());
        if(accountOptional.isPresent()){
           Account account = accountOptional.get();
           if(passwordEncoder.matches(auth.getPassword(), account.getPassword())){
               String value = UUID.randomUUID().toString();
               Token token = Token.builder().value(value).account(account).build();
               tokensRepository.save(token);
               return new TokenDto(value);
           } else {
               throw new IllegalArgumentException("Wrong email/password");
           }
        } else {
            throw new IllegalArgumentException("Wrond email/password");
        }
    }
}
