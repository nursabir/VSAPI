package com.example.VSAPIBot.DTO;

import com.example.VSAPIBot.Project.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String title;
    private ManagerDTO infoManager;
    private ModeratorDTO infoModer;
    private String comment;
    private String state;
    private String requirements;
    private String descriptionProject;
    private String type;

    public static ProjectDTO from(Project project){
        return ProjectDTO.builder()
                .id(project.getId())
                .requirements(project.getRequirements())
                .state(project.getState())
                .comment(project.getComment())
                .title(project.getTitle())
                .infoModer(ModeratorDTO.from(project.getInfoModer()))
                .infoManager(ManagerDTO.from(project.getInfoManager()))
                .descriptionProject(project.getDescriptionProject())
                .type(project.getType())
                .build();
    }

    public static List<ProjectDTO> from(List<Project> projects){
        return projects.stream().map(ProjectDTO::from).collect(Collectors.toList());
    }

}
