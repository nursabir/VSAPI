package com.example.VSAPIBot.Response;

import com.example.VSAPIBot.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    Optional<Response> findById(Long id);

    Response save(Response response);
}
