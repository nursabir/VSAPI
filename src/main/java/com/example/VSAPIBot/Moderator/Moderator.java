package com.example.VSAPIBot.Moderator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "moderator")
public class Moderator {
    @Id
    @Column(name = "login_moderator")
    private Long login;

    public Long getLogin() {
        return login;
    }

    public void setLogin(Long login) {
        this.login = login;
    }
}
