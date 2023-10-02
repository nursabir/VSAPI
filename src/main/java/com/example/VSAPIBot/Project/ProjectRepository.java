package com.example.VSAPIBot.Project;

import com.example.VSAPIBot.Manager.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAll();

    Optional<Project> findById(Long id);
    Optional<Project> findByStateAndInfoManager(String stateProject, Manager loginModerator);

    Project save(Project project);
}
