package com.example.VSAPIBot.Project;

import com.example.VSAPIBot.DTO.ManagerDTO;
import com.example.VSAPIBot.DTO.ProjectDTO;
import com.example.VSAPIBot.Manager.Manager;
import com.example.VSAPIBot.service.ProjectServiceImpl;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.util.List;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    @Autowired
    private ProjectServiceImpl projectService;

    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(projectService.getProjectById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{modification}/{id}")
    @ResponseBody
    public ResponseEntity<List<ProjectDTO>> getProjectByStateAndInfoManager(
            @PathVariable("modification") String stateProject,
            @PathVariable("id") Long idManager) {
        try {
            return ResponseEntity.ok().body(projectService.getProjectByStateAndInfoManager(stateProject, idManager));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
//        try {
//            Project project = projectRepository.findByStateAndInfoManager(stateProject, infoManager)
//                    .orElseThrow(() -> new ResourceNotFoundException("Project not found with state: " + stateProject
//                            + " and infoManager: " + infoManager));
//            return ResponseEntity.ok().body(project);
//        } catch (ResourceNotFoundException ex) {
//            logger.error("Error while fetching project by state and infoManager ", ex);
//            return ResponseEntity.notFound().build();
//        } catch (Exception ex) {
//            logger.error("Internal server error ", ex);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }



    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO project) {
        System.out.println(project.toString());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(project));
        } catch (Exception e) {
            logger.error("Error while creating project ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
//        System.out.println(project.getTitle());
//        try {
//            Project createdProject = projectRepository.save(project); //todo вот тут теперь добавляем все прочее
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
//        } catch (Exception ex) {
//            logger.error("Error while creating project ", ex);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable("id") Long idProject, @RequestBody ProjectDTO
            projectDetails) {
        try{
           return ResponseEntity.ok().body(projectService.updateProject(idProject, projectDetails));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
//        try {
//            Project project = projectRepository.findById(idProject)
//                    .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + idProject));
//
//            //project.setTitle(projectDetails.getTitle());
//            //project.setType(projectDetails.getType());
//            // project.setDescriptionProject(projectDetails.getDescriptionProject());
//            //   project.setRequirements(projectDetails.getRequirements());
//            project.setStateProject(projectDetails.getStateProject());
////            project.setComment(projectDetails.getComment());
//            project.setInfoModer(projectDetails.getInfoModer());
//
//            Project updatedProject = projectRepository.save(project);
//            return ResponseEntity.ok().body(updatedProject);
//        } catch (ResourceNotFoundException ex) {
//            logger.error("Error while updating project - Project not found ", ex);
//            return ResponseEntity.notFound().build();
//        } catch (Exception ex) {
//            logger.error("Error while updating project ", ex);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        try {
            return ResponseEntity.ok().body(projectService.getAllProjects());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}