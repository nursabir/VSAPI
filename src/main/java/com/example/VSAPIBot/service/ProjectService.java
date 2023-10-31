package com.example.VSAPIBot.service;

import com.example.VSAPIBot.DTO.ManagerDTO;
import com.example.VSAPIBot.DTO.ProjectDTO;
import com.example.VSAPIBot.Project.Project;

import java.util.List;

public interface ProjectService {

    ProjectDTO getProjectById(Long id);

    List<ProjectDTO> getProjectByStateAndInfoManager(String stateProject, Long idManager);

    ProjectDTO createProject(ProjectDTO projectDTO);

    ProjectDTO updateProject(Long id);

    List<ProjectDTO> getAllProjects();

    ProjectDTO updateProject(Long id, ProjectDTO projectDTODetails);

}
