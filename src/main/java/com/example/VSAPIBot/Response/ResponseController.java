package com.example.VSAPIBot.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/response")
public class ResponseController {
    private static final Logger logger = LoggerFactory.getLogger(ResponseController.class);

    @Autowired
    private ResponseRepository responseRepository;

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Response> createResponse(@RequestBody Response response) {
        try {
            Response createdResponse = responseRepository.save(response);
            return new ResponseEntity<>(createdResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error by creating response ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
