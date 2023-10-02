package com.example.VSAPIBot.Staff;

import com.example.VSAPIBot.Project.Project;
import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Staff")
    private Long id;

    @Column(name = "id_manager")
    private Long managerId;

    @Column(name = "id_user")
    private Long userId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_Project")
    private Project idProject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Project getProjectId() {
        return idProject;
    }

    public void setProjectId(Project idProject) {
        this.idProject = idProject;
    }
}

