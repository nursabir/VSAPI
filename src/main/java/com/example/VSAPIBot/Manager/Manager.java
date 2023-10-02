package com.example.VSAPIBot.Manager;

import jakarta.persistence.*;

@Entity
@Table(name = "project_manager")
public class Manager {
    //    @Id
    @Column(name = "login_Manager")
    private Long login;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getLogin() {
        return login;
    }

    public void setLogin(Long login) {
        this.login = login;
    }
}
