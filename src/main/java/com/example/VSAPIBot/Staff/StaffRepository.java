package com.example.VSAPIBot.Staff;

import com.example.VSAPIBot.Manager.Manager;
import com.example.VSAPIBot.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByStateAndManagerId(String state, Long managerId);

    List<Staff> findByIdProject(Project idProject);

    Staff findByidProject(Project idProject);

    Staff save(Staff staff);
}
