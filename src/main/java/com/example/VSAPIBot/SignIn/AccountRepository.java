package com.example.VSAPIBot.SignIn;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> getAccountByEmail(String email);
    boolean existsByConfirmId(String confirmId);
    Optional<Account> getAdminByConfirmId(String confirmId);

    Optional<Account> findByTokens_Value(String value);
}
