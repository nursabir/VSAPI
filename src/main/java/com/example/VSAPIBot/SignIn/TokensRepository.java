package com.example.VSAPIBot.SignIn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokensRepository extends JpaRepository<Token, Long> {

}
