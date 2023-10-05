package com.example.VSAPIBot.Project;

import com.example.VSAPIBot.Moderator.Moderator;
import com.example.VSAPIBot.Manager.Manager;
import jakarta.persistence.*;


@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Project")
    private Long id;
    @Column(name = "title")
    private String title;

    public String getDescriptionProject() {
        return descriptionProject;
    }

    public void setDescriptionProject(String descriptionProject) {
        this.descriptionProject = descriptionProject;
    }

    //    public String getDescriptionProject() {
//        return descriptionProject;
//    }
//
//    public void setDescriptionProject(String description) {
//        this.descriptionProject = description;
//    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "type")
    private String type;
    @Column(name = "description_Project")
    private String descriptionProject;
    @Column(name = "requirements")
    private String requirements;
    @Column(name = "state_Project")
    private String state;
    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "login_Manager")
    private Manager infoManager;

    @ManyToOne
    @JoinColumn(name = "login_Moderator")
    private Moderator infoModer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public String getDescriptionProject() {
//        return descriptionProject;
//    }
//
//    public void setDescriptionProject(String description) {
//        this.descriptionProject = description;
//    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getStateProject() {
        return state;
    }

    public void setStateProject(String state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Manager getInfoManager() {
        return infoManager;
    }

    public void setInfoManager(Manager infoManager) {
        this.infoManager = infoManager;
    }

    public Moderator getInfoModer() {
        return infoModer;
    }

    public void setInfoModer(Moderator infoModer) {
        this.infoModer = infoModer;
    }
}
