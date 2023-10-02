package com.example.VSAPIBot.Manager;

import com.example.VSAPIBot.Moderator.Moderator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final Logger logger = LoggerFactory.getLogger(ManagerController.class);
    private final ManagerRepository managerRepository;

    public ManagerController(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @GetMapping("/{login}")
    @ResponseBody
    public ResponseEntity<Manager> getUserByLogin(@PathVariable("login_Manager") Long login) {
        System.out.println(" попали в гет ");
        try {
            Manager manager = managerRepository.findByLogin(login);
            if (manager != null) {
                System.out.println(" попали в налл ");

                return new ResponseEntity<>(manager, HttpStatus.OK);
            } else {
                System.out.println(" хуйня какая-то ");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Failed to get user by login: {} ", login, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Void> createManager(@RequestBody Manager manager) {
        logger.info("");
        System.out.println(" создаем юзера ");
        System.out.println();
        try {
            System.out.println(manager.getLogin());
            Manager existingManager = managerRepository.findByLogin(manager.getLogin());
            if (existingManager != null) {
                System.out.println(" или здесь ");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
//                managerRepository.addManager(manager);
                managerRepository.save(manager); //todo ошибка тут реально

//                managerRepository.insertInTo(manager);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (Exception e) {
            logger.error("Failed to create manager ", e);

            System.out.println(" не дай бог тут");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Manager>> getAllManagers() {
        try {
            System.out.println(" Общаемся ебать");
            List<Manager> managers = managerRepository.findAll();
            return new ResponseEntity<>( managers,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("НЕ Общаемся ебать");
            logger.error("Failed to get all moderators", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
