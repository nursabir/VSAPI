package com.example.VSAPIBot.service;

import com.example.VSAPIBot.DTO.ProjectDTO;
import com.example.VSAPIBot.Manager.Manager;
import com.example.VSAPIBot.Manager.ManagerRepository;
import com.example.VSAPIBot.Moderator.Moderator;
import com.example.VSAPIBot.Project.Project;
import com.example.VSAPIBot.Project.ProjectRepository;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProjectServiceImpl implements ProjectService {
    private final Logger logger = org.apache.log4j.Logger.getLogger(ProjectServiceImpl.class);
    @Autowired

    ProjectRepository projectRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Override
    public ProjectDTO getProjectById(Long id) {
        return getProjectFromOptional(projectRepository.findById(id));
    }

    @Override
    public List<ProjectDTO> getProjectByStateAndInfoManager(String stateProject, Long loginManager) {
        try {
            List<Optional<Project>> projects = projectRepository.findAllByStateAndInfoManager(stateProject, managerRepository.findByLogin((loginManager)));
            List<ProjectDTO> list = new ArrayList<>();
            for (Optional<Project> project : projects) {
                list.add(getProjectFromOptional(project));
            }
            return list;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project newProject = Project.builder()
                .id(projectDTO.getId())
                .title(projectDTO.getTitle())
                .descriptionProject(projectDTO.getDescriptionProject())
                .comment(projectDTO.getComment())
                .infoManager(
                        Manager.builder().id(projectDTO.getInfoManager().getId()).login(projectDTO.getInfoManager().getLogin()).build())
                .state(projectDTO.getState())
                .requirements(projectDTO.getRequirements())
                .infoModer(
                        Moderator.builder().login(projectDTO.getInfoModer().getLogin()).build()
                )
                .type(projectDTO.getType())
                .build();

        logger.info("Проект сделан");
        return ProjectDTO.from(projectRepository.save(newProject));
    }

    @Override
    public ProjectDTO updateProject(Long id) {
        return null;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return ProjectDTO.from(projectRepository.findAll());
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTODetails) {
        Optional<Project> projectForUpdate = projectRepository.findById(id);
        ProjectDTO projectDTOForUpdate = getProjectFromOptional(projectForUpdate);
        projectDTOForUpdate.setInfoModer(projectDTODetails.getInfoModer());
        projectDTOForUpdate.setState(projectDTODetails.getState());

        Project newProject = Project.builder()
                .id(projectDTOForUpdate.getId())
                .title(projectDTOForUpdate.getTitle())
                .descriptionProject(projectDTOForUpdate.getDescriptionProject())
                .comment(projectDTOForUpdate.getComment())
                .infoManager(
                        Manager.builder().id(projectDTOForUpdate.getInfoManager().getId()).login(projectDTOForUpdate.getInfoManager().getLogin()).build())
                .state(projectDTOForUpdate.getState())
                .requirements(projectDTOForUpdate.getRequirements())
                .infoModer(
                        Moderator.builder().login(projectDTOForUpdate.getInfoModer().getLogin()).build()
                )
                .type(projectDTOForUpdate.getType())
                .build();

        return ProjectDTO.from(projectRepository.save(newProject));

    }


    private ProjectDTO getProjectFromOptional(Optional<Project> maybeProject) throws IllegalArgumentException {
        ProjectDTO projectDTO = null;
        if (maybeProject.isPresent()) {
            projectDTO = ProjectDTO.from(maybeProject.get());
        } else {
            logger.error("ProjectNotFound ");
            throw new IllegalArgumentException();
        }
        return projectDTO;
    }
}
