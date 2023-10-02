package com.example.VSAPIBot.Staff;

import com.example.VSAPIBot.Manager.Manager;
import com.example.VSAPIBot.Project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;
    private final Logger logger = LoggerFactory.getLogger(StaffController.class);

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping("/{state}/{id_manager}")
    @ResponseBody
    public ResponseEntity<List<Staff>> getStaffByStateAndManagerId(
            @PathVariable("state") String state,
            @PathVariable("id_manager") Long managerId) {
        try {
            List<Staff> staffList = staffRepository.findByStateAndManagerId(state, managerId);
            if (!staffList.isEmpty()) {
                return new ResponseEntity<>(staffList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error while getting staff by state and idManager: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{projectId}")
    @ResponseBody
    public ResponseEntity<List<Staff>> getStaffByIdProject(
            @PathVariable("idProject") Project idProject) {
        try {
            List<Staff> staffList = staffRepository.findByIdProject(idProject);
            if (!staffList.isEmpty()) {
                return new ResponseEntity<>(staffList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error while getting staff by state and idManager: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idProject}")
    @ResponseBody
    public ResponseEntity<Staff> updateStaffByIdProject(@PathVariable("idProject") Project idProject, @RequestBody Staff staff) {
        try {
            Staff existingStaff = staffRepository.findByidProject(idProject);
            if (existingStaff != null) {
                existingStaff.setManagerId(staff.getManagerId());
                existingStaff.setUserId(staff.getUserId());
                existingStaff.setComment(staff.getComment());
                existingStaff.setState(staff.getState());
                existingStaff.setProjectId(staff.getProjectId());
                Staff updatedStaff = staffRepository.save(existingStaff);
                return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error while updating staff by project id: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        try {
            Staff createdStaff = staffRepository.save(staff);
            return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while creating staff: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}