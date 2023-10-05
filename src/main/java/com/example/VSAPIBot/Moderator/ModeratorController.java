package com.example.VSAPIBot.Moderator;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moderator")
public class ModeratorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModeratorController.class);

    @Autowired
    private ModeratorRepository moderatorRepository;

    @GetMapping("/{login}")
    @ResponseBody
    public ResponseEntity<Moderator> getModerator(@PathVariable("login") Long loginModerator) {
        Moderator moderator;
        try {
            moderator = moderatorRepository.findByLogin(loginModerator)
                    .orElseThrow(() -> new EntityNotFoundException("Moderator not found with login: " + loginModerator));
            return ResponseEntity.ok(moderator);
        } catch (EntityNotFoundException e) {
            LOGGER.error("Error while retrieving moderator: {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred: {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Moderator>> getAllModerators() {
        try {
            System.out.println(" Общаемся ебать");
            List<Moderator> moderators = moderatorRepository.findAll();
            return new ResponseEntity<>(moderators, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("НЕ Общаемся ебать");
            LOGGER.error("Failed to get all moderators", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}