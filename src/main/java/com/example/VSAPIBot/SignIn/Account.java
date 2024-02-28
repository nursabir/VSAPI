package com.example.VSAPIBot.SignIn;//package com.example.tytyrmaboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@Entity
public class Account {

    public enum State {
        CONFIRMED, NOT_CONFIRMED, BAN
    }

    public enum Role {
        ADMIN, USER
    }

    @Column
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Id
    private String email;
    @Column
    private String password;

    @Enumerated
    private State state;

    @Column
    private String confirmId;

    @Enumerated
    private Role role;

    @OneToMany(mappedBy = "account")
    private List<Token> tokens;





}
