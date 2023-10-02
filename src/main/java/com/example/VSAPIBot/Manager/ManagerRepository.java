package com.example.VSAPIBot.Manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {
    Manager findByLogin(Long loginManager);



    Manager save(Manager manager);


//    default void addManager(Manager manager) {
//        save(manager);
//    }
}